package org.rambo.fello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Rambo Yang
 * @date 2017/07/17
 */
@Controller
public class DownloadController {

    @RequestMapping("/fello/server/framework/nfis-freemarker-tools")
    public void downloadNfisFreemarker(HttpServletRequest request, HttpServletResponse response) {
        String filePath = request.getServletContext().getRealPath("/") + File.separator;
        String fileName = "nfis-freemarker-tools.tar";

        download(request, response, filePath, fileName);
    }


    @RequestMapping("/fello/server/server-jar")
    public void downloadJ2EEServer(HttpServletRequest request, HttpServletResponse response) {
        String filePath = request.getServletContext().getRealPath("/") + File.separator;
        String fileName = "j2ee-server-jar-with-dependencies.jar";

        download(request, response, filePath, fileName);

    }

    private void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        try {
            response.setHeader("conent-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("ISO-8859-1"), "UTF-8"));
            response.setContentType("application/octet-stream");

            File file = new File(filePath + fileName);
            InputStream is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);

            int length = 0;
            byte[] temp = new byte[1 * 1024 * 10];

            OutputStream os = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            while ((length = bis.read(temp)) != -1) {
                bos.write(temp, 0, length);
            }
            bos.flush();
            bis.close();
            bos.close();
            is.close();

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
