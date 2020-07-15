package com.wlrss.oldmarket.MvcConfig;

import org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPrivateKey;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(
                        "/404.html",
                        "/about.html",
                        "/activation-success.html",
                        "/blog.html",
                        "/blog-single.html",
                        "/cart.html",
                        "/contact.html",
                        "/index.html",
                        "/item-detail.html",
                        "/items.html",
                        "/login-register.html",
                        "/moreclick.html",
                        "/my-sales.html",
                        "/order-success.html",
                        "/payment_gaoji.html",
                        "/payment_putong.html",
                        "/payment_zungui.html",
                        "/pricing.html",
                        "/search-results.html",
                        "/",
                        "/MyLogin",
                        "/logout",
                        "/activation/**",
                        "/register",
                        "/goods/**",
                        "/order/**",
                        "/items/**",
                        "/user/**",
                        "/comment/**",
                        "/file",
                        "/fileUpload",
                        "/address/**",
                        "/orderDetail/**",
                        "/cart",
                        "/add",
                        "/remove",
                        "/settlement",
                        "/addAddress.html",
                        "/addOrder",
                        "/success",
                        "/jump/**",
                        "/jump/it/**",
                        "/blog/**",
                        "/item-detail?id=**",
                        "/jump/**"
                ).permitAll()
                //只有管理员才能访问
                .antMatchers("/admin/**","/admin/page/**","/admin/**/page/**").hasRole("admin");
        http.formLogin();
        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("http://localhost:8080/jump/main");
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new  BCryptPasswordEncoder().encode("123456")).roles("admin")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("admin");
    }
}
