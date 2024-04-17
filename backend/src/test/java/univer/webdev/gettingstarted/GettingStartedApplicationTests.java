package univer.webdev.gettingstarted;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GettingStartedApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void createProject() throws Exception{
		mockMvc.perform(
						post("/projects")
								.contentType(MediaType.APPLICATION_JSON)
								.content(
										"{ \"name\": \"asdf\"," +
												"\"description\": \"lorem ipsum\"," +
												"\"begin\": \"2024-02-23\"," +
												"\"end\": \"2024-02-23\"}"
								)
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().is(201))
				.andExpect(jsonPath("$.name").value("asdf"))
				.andExpect(jsonPath("$.description").value("lorem ipsum"))
				.andExpect(jsonPath("$.begin").value("2024-02-23"))
				.andExpect(jsonPath("$.end").value("2024-02-23"))
				.andDo(print());
	}

}
