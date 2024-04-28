## Spring security using Jdbc Template



**Jdbc Template used for Spring security**


**ProjectSecurityConfiguration  (java/com/ps/springsecuritycustomauth/config/ProjectSecurityConfiguration.java)**

is used to create the bean UserDetailsService which is ootb is getting user from data base and validating against the user 


_@Bean
public UserDetailsService userDetailsService(DataSource dataSource) {
return new JdbcUserDetailsManager(dataSource);
}_

database is postgresql which is used as docker image.

database connection is mentioned in application.yaml file




