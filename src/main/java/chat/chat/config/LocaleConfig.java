// package chat.chat.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.LocaleResolver;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.i18n.SessionLocaleResolver;
// import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
// import java.util.Locale;


// @Configuration
// public class LocaleConfig implements WebMvcConfigurer {

//     @Bean
//     public LocaleResolver localeResolver() {
//         SessionLocaleResolver slr = new SessionLocaleResolver();
//         slr.setDefaultLocale(new Locale("tr")); // Varsayılan: Türkçe
//         return slr;
//     }

//     @Bean
//     public LocaleChangeInterceptor localeChangeInterceptor() {
//         LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//         interceptor.setParamName("lang"); // URL parametresi ?lang=tr, ?lang=en
//         return interceptor;
//     }

//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(localeChangeInterceptor());
//     }
// }

