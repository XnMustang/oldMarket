package com.wlrss.oldmarket.config;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 10:06
 */
public class AlipayConfig {
    // 应用ID,
    public static String app_id = "2016101800717213";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZJgbDnPeYVMHV1f9L29b/kc19gli2TbE0oIFEeUgKWcEM5Qc3Wko5aW8NCEQqLkEqRuASjEOKt0DctVc6Q4hw3EDq1YoNT/3Xw1ehue4hQImQhW/8K3sRgP+aa1ULdmU/bMCwTivIrwTAL3btqLiPs58/L+ZkYj/Un480Y9YIrRa1cK6YBemEd//4tBLM/F+zRB6OE70AoineW2l+Nl8IaoVgOa94FwfLvHWL7bdKyIWQKz1UejIX1RxmaFt60vKGYN7KuMPfO4FzlsYBrRhXOUAux/xcJre94D/zULv3acruJuygBlE/VsODQBMUPzC/HK1bYVPhEE/M3Ky2fIr3AgMBAAECggEAa4/OnmLHbwDQZux28wODXFo+FhGPVvL4K7szLzFzTHm/YdwVlmXezbXkXiXZIjr8X8IJrF1BLtMIRjtC+VPnJ0U6fvTdkf4Fjh3PlXHt8wLN22gsv9KWDDQq0Xzi7TsJX2N1bh5pF/j8gDKV99NRVPRWO13cFdL/e8lZHfWkpNA8XkFt/b6Lh+gHnEdenRrk9iP8bBA85jQvvFDSKAIoxhST7+nKJwk8mN4kxHvP1dWUvPKmWfw9iA3MHVH0L/MmcCtRLiOv/ZtqhJG4iTXj1S27xEjzEWpa/yTHI6so5UIhYrkStbrYTP+yq65rsUXrLoFxkRrYfEnQ+AVQUpqQyQKBgQDgTyF5yORt6eu8jz0wv8CStP39ee0a1AZeh0q9TgRhjX4FHSWWK8lxa3cKx6quGC5p+fvYnvZBSUXWXfWnHseuX4BBieokGU1sW/B99yV7j3bRw08i3Vrt75Ft5fKsf+LcdFbV8JR/u9ZkR6SLPAZN9bk2OesYHz4Zv55YStaQpQKBgQCuySRiovqcSZD3NZFzsCIfORQFR/AZ4zuBNfhIKW4z0+/jVH5mQaUJRl28IYi6W1hCbTzXBseiinRmAEWfwQJqVjhU5ffrSdclK97fCVIKsi62a+9tym1qNsfDj7zia5dsi0B3VRBPcVvx04lKrboy3g1ombhuYnbDmSK4MrPeawKBgD4MhbUbplzlVYmukf23PX0Xxh7lKHrAZhamFD2zpDCcJF0jx+73o9zVHnQD8aoBFG3KcillJ7e9ni0Dht24/QG2d1Mp5HQh77Hm6OrfgCXi9Lpn7a82Tl39ZFxjPLMNGWNkZ1T9HQcMnezViKXfb9sG+zoN4YOluNcEk7fW0+3lAoGBAIhlsT5EHzwtcLJqHErijY4+FbCVVfptyHbp7goqQrSxVIfb3RPhcHAPeICizrdXJ0YXRx4qTUo7r+AVoD+t+DuRcz5VhCYaJon8dV0qQdrU9/0lt9++06nKfBPRrL4o5i8vcCxvtl24gd7yjDY54o9ll6MkKrgBoNq9O0nxT0pnAoGBAKzvTKHK2l0VDp8NB/D5oxFXU1eYdN0rvDZ09l5fCNsQoOBh1TUty5nQTswjspYV+PZWynP2gqMNYvHOSKih+pYtw//7SxsNCa2b57mU3yMc9IrVeIU1AsAgOHIcVfbLAZL0fx4pAsNF8mTq6GYDOUV7/Tu4RxcWybmcSAw5XZ/O";

    // 支付宝公钥,
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmSYGw5z3mFTB1dX/S9vW/5HNfYJYtk2xNKCBRHlIClnBDOUHN1pKOWlvDQhEKi5BKkbgEoxDirdA3LVXOkOIcNxA6tWKDU/918NXobnuIUCJkIVv/Ct7EYD/mmtVC3ZlP2zAsE4ryK8EwC927ai4j7OfPy/mZGI/1J+PNGPWCK0WtXCumAXphHf/+LQSzPxfs0QejhO9AKIp3ltpfjZfCGqFYDmveBcHy7x1i+23SsiFkCs9VHoyF9UcZmhbetLyhmDeyrjD3zuBc5bGAa0YVzlALsf8XCa3veA/81C792nK7ibsoAZRP1bDg0ATFD8wvxytW2FT4RBPzNystnyK9wIDAQAB";

    // 服务器异步通知页面路径
    public static String notify_url = "http://localhost:8080/success";

    // 页面跳转同步通知页面路径
    public static String return_url = "http://localhost:8080/success";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志
    public static String log_path = "c:\\";
}
