package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		jedis.set("name",list.toString());
		jedis.set("NAME", "lixiaohong");
		System.out.println(jedis.get("name"));
		System.out.println(jedis.get("NAME"));

	}

	@After
	public void after() {
		System.out.println("THIS ACTION IS JUST FOR TEST!");
	}

	@Test
	public void calcTelNums() {
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
				.stream().filter(e -> e % 2 != 0 || e == 0)
				.collect(Collectors.toList());
		System.out.println(
				new StringBuffer()
						.append(nums.get(0)).append(nums.get(4)).append(nums.get(4))
						.append(nums.get(2)).append(nums.get(5)).append(nums.get(1)).append(nums.get(2))
						.append(nums.get(1)).append(nums.get(2)).append(nums.get(5)).append(nums.get(5))
		);
	}
}
