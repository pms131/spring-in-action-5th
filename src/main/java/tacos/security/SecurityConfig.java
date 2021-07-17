package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/design", "/orders")
                        .access("hasRole('ROLE_USER')")
                    .antMatchers("/", "/**").access("permitAll")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
//                    .disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
        ;
    }


/*
 * InMemory 기반 인증 방식
 * 장점 : 테스트 목적이나 간단한 애플리케이션에는 편하다
 * 단점 : 사용자 추가, 삭제, 변경을 해야한다면 보안 구성 코드를 변경ㅎ나 후 애플리케이션을 다시 빌드, 배포, 설치해야 한다.
 *
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}password1")
                .authorities("ROLE_USER")
                .and()
                .withUser("user2")
                .password("{noop}password2")
                .authorities("ROLE_USER")
                ;
    }
    */

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users " +
                        "where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities " +
                        "where username = ?")
                .passwordEncoder(new NoEncodingPasswordEncoder())
        ;
    }
    */

}
