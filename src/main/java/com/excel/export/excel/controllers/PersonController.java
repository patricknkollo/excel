package com.excel.export.excel.controllers;

import com.excel.export.excel.entities.Person;
import com.excel.export.excel.repositories.PersonRepository;
import com.excel.export.excel.services.PersonExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Component
@RequestMapping(path ="/api/controller/person" )
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=person" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Person> personList = repository.findAll();
        PersonExcelGenerator generator = new PersonExcelGenerator(personList);
        generator.generateExcelFile(response);
    }

    @GetMapping("/page2")
    public @ResponseBody List<Person> indexHtml(){
      return repository.findAll();
    }

    @GetMapping("/page")
    public @ResponseBody String indexHtml2(){
        return "index";
    }

    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h2>HTML Forms</h2>\n" +
                "\n" +
                "<form action=\"/action_page.php\">\n" +
                "    <label for=\"fname\">First name:</label><br>\n" +
                "    <input type=\"text\" id=\"fname\" name=\"fname\" value=\"John\"><br>\n" +
                "    <label for=\"lname\">Last name:</label><br>\n" +
                "    <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Doe\"><br><br>\n" +
                "    <input type=\"submit\" value=\"Submit\">\n" +
                "</form>\n" +
                "\n" +
                "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/action_page.php\".</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
