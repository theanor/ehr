package be.monolith.ehr.ag.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import be.monolith.ehr.iam.AuthenticationServiceException;
import be.monolith.ehr.iam.IamService;
import be.monolith.ehr.iam.IamServiceConstants;
import be.monolith.ehr.iam.Token;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends ZuulFilter {

	private static final String AUTH_HEADER = "Authorization";
	private static final String AUTH_HEADER_PATTERN_TOKEN_GROUP = "token";
	private static final Pattern AUTH_HEADER_PATTERN = Pattern
			.compile("^[Bb]earer\b(<" + AUTH_HEADER_PATTERN_TOKEN_GROUP + ">.*)$");

	private IamService iamService;

	public AuthenticationFilter(IamService iamService) {
		super();
		this.iamService = iamService;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		log.info("got request with serviceId " + ctx.get("serviceId").toString());
		return !IamServiceConstants.SERVICE_ID.equalsIgnoreCase(ctx.get("serviceId").toString());
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();

		HttpServletRequest request = ctx.getRequest();

		String token = getToken(request);

		if (token == null) {
			throw new ZuulException("all requests must bear valid token", HttpStatus.UNAUTHORIZED.value(),
					"empty or invalid token");
		}

		final Token parsedToken;

		try {
			parsedToken = check(token);
		} catch (AuthenticationServiceException e) {
			throw new ZuulException("all requests must bear valid token", HttpStatus.UNAUTHORIZED.value(),
					"empty or invalid token");
		}

		log.debug("received request from " + parsedToken.getClaims().get("sub"));

		return null;
	}

	private Token check(String token) throws AuthenticationServiceException {
		return iamService.check(token);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader(AUTH_HEADER);
		if (header == null)
			return null;

		Matcher matcher = AUTH_HEADER_PATTERN.matcher(header);
		if (matcher.matches()) {
			return matcher.group(AUTH_HEADER_PATTERN_TOKEN_GROUP);
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
