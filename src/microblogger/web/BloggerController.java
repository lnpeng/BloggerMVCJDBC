package microblogger.web;

import microblogger.db.Blogger;
import microblogger.db.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    private BloggerRepository bloggerRepository;

    @Autowired
    public BloggerController(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Blogger());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            BloggerForm bloggerForm,
            RedirectAttributes model,
            Errors errors) throws IllegalStateException, IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        Blogger blogger = bloggerForm.toBlogger();
        bloggerRepository.save(blogger);
        model.addAttribute("username", blogger.getUsername());
        model.addFlashAttribute("blogger", blogger);
        return "redirect:/blogger/{username}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showBloggerProfile(
            @PathVariable String username, Model model) {
        if (!model.containsAttribute("blogger")) {
            Blogger blogger = bloggerRepository.findByUsername(username);
            model.addAttribute(blogger);
        }
        return "profile";
    }
}
