package com.julioceno.qrcodeservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureDataMongo
class QrcodeserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
