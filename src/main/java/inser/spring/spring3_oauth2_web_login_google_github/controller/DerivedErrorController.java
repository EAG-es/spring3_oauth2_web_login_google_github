package inser.spring.spring3_oauth2_web_login_google_github.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DerivedErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        String errorMessage = "";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer httpErrorCode = Integer.valueOf(status.toString());
            switch (httpErrorCode) {
                case 400: {
                    errorMessage = "Http Error Code: 400. Bad Request";
                    break;
                }
                case 401: {
                    errorMessage = "Http Error Code: 401. Unauthorized";
                    break;
                }
                case 404: {
                    errorMessage = "Http Error Code: 404. Resource not found";
                    break;
                }
                case 500: {
                    errorMessage = "Http Error Code: 500. Internal Server Error";
                    break;
                }
                default: {
                    errorMessage = "Http Error Code: " + httpErrorCode;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "pages/error";
    }
}
