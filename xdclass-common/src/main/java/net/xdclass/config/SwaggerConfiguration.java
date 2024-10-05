package net.xdclass.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/5 16:54
 */
@Component
@Data
@EnableWebMvc
//springboot 版本需要查找对应的swagger版本
@EnableOpenApi
public class SwaggerConfiguration {

    /***
     * 对C端用户的接口文档
     * 也就是在swagger的界面,地址如下:http://localhost:9001/swagger-ui/index.html#/
     * 在这里会有所有的接口，包括管理端和用户端....
     * 所以在这里的groupname就是 各个端的名字
     * 此外这里的代码会改即可无需会写
     * @return
     */
    @Bean
    public Docket webApiDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("用户端接口文档")
                .pathMapping("/")
                // 定义是否开启swagger，false为关闭，可以通过变量控制，线上关闭
                .enable(true)
                //配置api文档元信息
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                //正则匹配请求路径，并分配至当前分组
                .paths(PathSelectors.ant("/api/**"))
                .build()
                //新版swagger3.0配置
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    }

    /***
     * 全局请求参数
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("登录令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
//        parameters.add(new RequestParameterBuilder()
//                .name("version")
//                .description("版本号")
//                .required(true)
//                .in(ParameterType.HEADER)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
        return parameters;
    }


    /***
     * 对管理端的接口文档
     * @return
     */
    @Bean
    public Docket adminApiDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("管理端接口文档")
                .pathMapping("/")
                // 定义是否开启swagger，false为关闭，可以通过变量控制，线上关闭
                .enable(true)
                //配置api文档元信息
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                //正则匹配请求路径，并分配至当前分组
                .paths(PathSelectors.ant("/admin/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("1024电商平台").description("微服务接口文档")
                .contact(new Contact("程序狗没有春天", "http://www.xdclass.net", "1585441936@qq.com"))
                .version("1.0").build();
    }

    /**
     * 生成通用响应信息
     * @return
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("4xx").description("请求错误，根据code和msg检查").build());
        return responseList;
    }
}
