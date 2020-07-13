package com.wlrss.oldmarket.MvcConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jamesBond
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/about.html").setViewName("about");
        registry.addViewController("/blog.html").setViewName("blog");
        registry.addViewController("/blog-single.html").setViewName("blog-single");
        registry.addViewController("/cart.html").setViewName("cart");
        registry.addViewController("/contact.html").setViewName("contact");
        registry.addViewController("/dash-items.html").setViewName("dash-items");
        registry.addViewController("/dash-messages.html").setViewName("dash-messages");
        registry.addViewController("/dash-profile.html").setViewName("dash-profile");
        registry.addViewController("/dash-reviews.html").setViewName("dash-reviews");
        registry.addViewController("/dash-sales.html").setViewName("dash-sales");
        registry.addViewController("/dashboard.html").setViewName("dashboard");
        registry.addViewController("/items.html").setViewName("items");
        registry.addViewController("/item-detail.html").setViewName("item-detail");
        registry.addViewController("/login-register.html").setViewName("login-register");
        registry.addViewController("/moreclick.html").setViewName("moreclick");
        registry.addViewController("/order-success.html").setViewName("order-success");
        registry.addViewController("/payment-info.html").setViewName("payment-info");
        registry.addViewController("/pricing.html").setViewName("pricing");
        registry.addViewController("/search-results.html").setViewName("search-results");
        registry.addViewController("/my-sales.html").setViewName("my-sales");
        registry.addViewController("/dash-addItem.html").setViewName("dash-addItem");
        registry.addViewController("/activation-success.html").setViewName("activation-success");
        registry.addViewController("/payment_putong.html").setViewName("payment_putong");
        registry.addViewController("/payment_gaoji.html").setViewName("payment_gaoji");
        registry.addViewController("/payment_zungui.html").setViewName("payment_zungui");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns(
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
                        "/login",
                        "/logout",
                        "/activation/**",
                        "/register",
                        "/goods/**",
                        "/order/**",
                        "/items/**",
                        "/user/**",
                        "/Comment/**",
                        "/file",
                        "/fileUpload",
                        "/address/**",
                        "/orderDetail/**",
                        "/goodsMessage/**",
                        "/shopping/**",
                        "/pay/**"
                ).excludePathPatterns(
                        "/admin/**",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/vendor/**",
                        "/layui/**"
                );
    }
}
