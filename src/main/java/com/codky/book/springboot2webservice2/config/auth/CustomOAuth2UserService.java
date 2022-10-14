package com.codky.book.springboot2webservice2.config.auth;

import com.codky.book.springboot2webservice2.config.auth.dto.OAuthAttributes;
import com.codky.book.springboot2webservice2.config.auth.dto.SessionUser;
import com.codky.book.springboot2webservice2.domain.user.User;
import com.codky.book.springboot2webservice2.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 현재 로그인 진행중인 서비스를 구분하는 코드(구글,네이버,카카오 ..)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth2 로그인 진행시 키가 되는 필드값(PK), 구글은 지원하지만("sub") 네이버,카카오등은 미지원

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스
        User user = saveOrUpdate(attributes);

        // 세션에 사용자정보를 저장하기 위한 Dto 클래스
        // User 클래스를 안쓰고 SessionUser를 만들어서 쓰는 이유는 User를 세션에 저장할때 직렬화를 구현하지 않으면 에러가 발생함.
        // 그렇다고 엔티티 User클래스에 직렬화 코드를 넣으면 다른 엔티티와 관계형성시 성능이슈, 부수효과 발생확률이 높아짐.
        // 그래서 직렬화 기능을 가진 세션 Dto(SessionUser)를 하나 추가로 만드는 것이 이후 운영 및 유지보수때 많은 도움이 됨.
        httpSession.setAttribute("user", new SessionUser(user));

        System.out.println("squash test");

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
