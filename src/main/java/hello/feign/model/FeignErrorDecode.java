package hello.feign.model;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import hello.feign.utils.FeignResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

/**
 *  요청 결과가 5xx 대 에러라면, 요청을 다시 시도 한다.
 *  <p>단, {@link hello.tistory.feign.config.FeignRetryConfiguration} 을 feign client 에 설정 해야한다.
 */
@Slf4j
public class FeignErrorDecode implements ErrorDecoder {

    /**
     * <dl>
     * <dt>5xx 에러가 지속적으로 발생한다면</dt>
     * <dd>RetryableException 발생하고, 더이상 Retry 를 할 수 없을때 여기서 throw 한 RetryableException 이 응답으로 내려간다.</dd>
     * </dl>
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        log.error("{} 요청이 성공하지 못했습니다. status: {} requestUrl: {}, requestBody: {}, responseBody: {}",
                response.status(), methodKey, response.request().url(), FeignResponseUtils.getRequestBody(response), FeignResponseUtils.getResponseBody(response));

        if (isRetry(response)) {
            return new RetryableException(response.status(), format("%s 요청이 성공하지 못했습니다. Retry 합니다. - headers: %s", methodKey, response.headers()),
                   response.request().httpMethod(), null, response.request());
        }

        return new IllegalStateException(format("%s 요청이 성공하지 못했습니다. - cause: %s, headers: %s", methodKey, response.status(), response.headers()));
    }

    /**
     * 5XX 에러이면서, GET 요청에 대해서만 retry 한다. 그 이외의 경우에 retry 가 필요하면 별도의 configuration class 에서 ErrorDecoder 를 설정한다.
     */
    private boolean isRetry(Response response) {
        return Request.HttpMethod.GET != response.request().httpMethod() && HttpStatus.valueOf(response.status()).is5xxServerError();
    }
}
