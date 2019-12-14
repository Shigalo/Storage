package by.bsuir.shigalo7.Controllers;

import by.bsuir.shigalo7.Services.InfoService;
import by.bsuir.shigalo7.Services.TourService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;

@Controller
@RequestMapping
public class ImageController {

    @Autowired
    private InfoService infoService;
    @Autowired
    private TourService tourService;

    @RequestMapping(value = "/infoImage/{id}")
    public void getInfoImage(HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        response.setContentType("image/jpeg");

        Blob picture = infoService.findByID(id).getPicture();

        byte[] bytes = picture.getBytes(1, (int) picture.length());
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/tourImage/{id}")
    public void getTourImage(HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        response.setContentType("image/jpeg");

        Blob picture = tourService.findById(id).getPicture();

        byte[] bytes = picture.getBytes(1, (int) picture.length());
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
