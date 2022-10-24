package FranksDelight.webapp.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StripeResponse {
    private String clientSecret;
}