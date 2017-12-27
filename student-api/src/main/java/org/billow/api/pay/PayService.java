package org.billow.api.pay;

import java.util.Map;

public interface PayService {

    void savePayData(Map<String, String> map) throws Exception;
}
