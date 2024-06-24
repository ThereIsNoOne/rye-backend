package com.io.rye.rye.service;

import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.mappers.ResultMapper;
import com.io.rye.rye.repository.KidRepository;
import com.io.rye.rye.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ResultServiceTests {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private KidRepository kidRepository;

    @InjectMocks
    private ResultService resultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddResult_ValidParameters() {
        // Mock data
        ResultDto resultDto = new ResultDto(/* provide necessary parameters */);
        Result mockResult = new Result(/* provide necessary parameters */);

        // Mock behavior of ResultMapper.fromDto
        when(ResultMapper.fromDto(resultDto, kidRepository, Integer.parseInt("123")))
                .thenReturn(mockResult);

        // Mock behavior of resultRepository.save
        when(resultRepository.save(Mockito.any(Result.class)))
                .thenReturn(mockResult); // Assuming save returns the saved result

        // Mock behavior of findById in resultRepository
        when(kidRepository.findById(anyInt()))
                .thenReturn(Optional.of(new Kid(/* provide necessary parameters */))); // Mocking findById to return an Optional containing a Kid

        // Call the method under test
        Result result = resultService.addResult(resultDto, "123");

        // Assertions
        assertEquals(mockResult, result); // Ensure the returned result is the same as mockResult
    }

    @Test
    void testGetAllResults() {
        // Mock repository response
        when(resultRepository.findByKid(anyString()))
                .thenReturn(Arrays.asList(new Result(),
                        new Result()));

        Iterable<Result> results = resultService.getAllResults("123");

        // Verify the size and content of the returned results
        List<Result> resultList = (List<Result>) results;
        assertEquals(2, resultList.size());
    }
}