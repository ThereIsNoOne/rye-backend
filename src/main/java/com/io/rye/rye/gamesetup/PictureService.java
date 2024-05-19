package com.io.rye.rye.gamesetup;

import com.io.rye.rye.dto.PictureDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class PictureService {


    @Value("${aws.db.url}")
    private String url;

    public PictureDto fetchRandom() {
        PictureDto pictureDto = new PictureDto();
        JSONObject response = sendRequest();

        JSONObject body = response.getJSONObject("body");
        pictureDto.setEmotion(body.getString("emotion"));
        pictureDto.setUrl(body.getString("image_url"));
        return pictureDto;
    }

    private JSONObject sendRequest() {
        RestTemplate template = new RestTemplate();


        String response = template.getForObject(url, String.class);
        return new JSONObject(response);
    }

    public Iterable<PictureDto> fetch(int quantity) {
        List<PictureDto> pictureDtos = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            //TODO: Uncomment this to enable full functionality
//            pictureDtos.add(fetchRandom());

            // TODO: For test purposes
            pictureDtos.add(
                    new PictureDto() {{
                        setUrl("https://cdn.britannica.com/55/75855-050-3D52AB80/giraffe-Kenya.jpg");
                        setEmotion("HAPPY");
                    }}
            );
        }
        return pictureDtos;
    }
}
