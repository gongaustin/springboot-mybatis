package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA Description: Author:GongJun Date:2017/8/29
 * Time:14:15 Chinawiserv Technologies Co., Ltd.
 */

@RequestMapping("test")
public class redisConn {
	private Jedis jedis;

	@Before
	public void setup() {
		/**
		 * 连接redis服务器
		 */
		jedis = new Jedis("127.0.0.1", 6379);
		/**
		 * 权限认证
		 */
		jedis.auth("123456");
	}

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("abc");
		jedis.set("NAME", "lixiaohong");
		System.out.println(jedis.get("name"));
		System.out.println(jedis.get("NAME"));

	}

	@After
	public void after() {
		System.out.println("THIS ACTION IS JUST FOR TEST!");
	}
}
