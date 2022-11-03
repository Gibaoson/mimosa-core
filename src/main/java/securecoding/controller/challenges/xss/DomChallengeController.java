package securecoding.controller.challenges.xss;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriComponentsBuilder;

import securecoding.controller.template.ChallengeController;
import securecoding.controller.template.ChallengeControllerAdapter;
import securecoding.model.Attempt;
import weilianglol.ixora.PageParser;
import weilianglol.ixora.PageReader;

@ChallengeController("/challenges/dom")
public class DomChallengeController extends ChallengeControllerAdapter {

	@Override
	public void unitTest(Attempt attempt, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nav = request.getParameter("nav");

		// Append page response
		String content = fetchPage("https://WelcomeAttacker", nav);
		attempt.getData().put("page", content);

		// Replace content
		String html = "<html><head></head><body><p>" + "https://WelcomeAttacker" + "</p></body></html>";
		PageParser parser = new PageParser(html);
		parser.update();

		// System.out.println(parser.msg);
		// Has an alert
		if (nav.indexOf("Name=") + 5 != 4)
			attempt.addPoints(10);
		if (nav.substring(nav.indexOf("Name=") + 5).contains("Bob"))
			attempt.addPoints(15);
		// if (parser.getAlerts().size() > 0)
		// 	attempt.addPoints(10);
		// if (parser.getAlerts().contains("xss_by_sam_tan"))
		// 	attempt.addPoints(15);
	}

	private String fetchPage(String mainPath, String url) throws Exception {
		PageReader pageReader = new PageReader("templates/pages/challenges/dom.html");
		url = url == null ? "" : url.trim();

		// Bad url
		if (url.isEmpty() || !url.startsWith(mainPath) || !(new URL(url).getHost().equals(new URL(mainPath).getHost())))
			return pageReader.getFragment("bad-request").html();

		// Get subpath
		String path = new URL(url).getPath();

		if (path.equals("") || path.equals("/name=")) {
			return pageReader.getFragment("main-site").html();

		}

		return pageReader.getFragment("not-found").html();
	}

}
