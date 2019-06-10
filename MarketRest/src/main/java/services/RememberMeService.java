package services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class RememberMeService extends TokenBasedRememberMeServices {
	private final static String TOKEN_STRING = "my_token";

    public RememberMeService(String key, org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    @Override
    protected String extractRememberMeCookie(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_STRING);
        if ((token == null) || (token.length() == 0)) {
            return "";
        }
        return token;
    }
}
