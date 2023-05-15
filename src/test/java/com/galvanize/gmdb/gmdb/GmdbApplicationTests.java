package com.galvanize.gmdb.gmdb;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class GmdbApplicationTests {
    private MockMvc mvc,mvc1;
    @InjectMocks
    private MoviesController moviesController;
    @Mock IMoviesRepository repo;
    @Mock IReviewerRepository repo1;
    private JacksonTester<Reviewer> jsonReviewer;
    private JacksonTester<Movies> jsonmovies;
    private JacksonTester<Review> jsonreview;
    @Autowired
    private WebApplicationContext context;
    // private 
    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc1=MockMvcBuilders.standaloneSetup(moviesController).build();
    }
    @Test
    public void getAllMovies() throws Exception {
        Movies movie1 = new Movies(1, "John wick", 2014, "Action", 180);
        Movies movie2 = new Movies(2, "Military Action", 2010, "Action", 200);
        Movies movie3 = new Movies(3, "Disaster", 2020, "Thriller", 100);

        List<Movies> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2); 
        movies.add(movie3);

        when(repo.findAll()).thenReturn(movies);
        mvc.perform(get("/movies/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void showReviews() throws Exception{
        Movies movies= new Movies (1,"Dream LEague Soccor", 2019,"Sports",90);
        Review reviwe =new Review("Boring","20-11-2009");
        
        when(repo.findById(1)).thenReturn(Optional.of(movies));
        mvc.perform(get("/movies/1")
            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }
    @Test
    public void getAllUSer() throws Exception {
        Reviewer reviewer1 = new Reviewer(1,"Ali","1-March-2023",5);
        Reviewer reviewer2 = new Reviewer(2,"Fatima","1-March-2023",2);
        Reviewer reviewer3 = new Reviewer(3,"Sabih","1-March-2023",5);
    
        List<Reviewer> reviewers = new ArrayList<>();
        reviewers.add(reviewer1);
        reviewers.add(reviewer2);
        reviewers.add(reviewer3);
        
        when(repo1.findAll()).thenReturn(reviewers);
        mvc1.perform(get("/reviewers/reviews")
            .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }
    @Test
    public void testCreateReviewer() throws Exception {
        Reviewer reviewer = new Reviewer("Sabih");
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
        mvc.perform(MockMvcRequestBuilders.post("/reviewers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReviewer.write(reviewer).getJson()));
                // .andExpect(status().isOk());
    }   
    @Test
	public void canDeleteMovies() throws Exception {
		Movies movie1 = new Movies(1, "Babies",1999 , "Kids Movies", 60);
        List<Movies> movies=new ArrayList<Movies>();
        movies.add(movie1);
		mvc.perform(MockMvcRequestBuilders.delete("/movies/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
    // @Test
	// public void DeleteByReviews()throws Exception {
	// 	// Review movie1 = new Review(1, 1, 3, "Bad", "20-11-30");
	// 	mvc.perform(MockMvcRequestBuilders.delete("/Reviewss/1"))
	// 			.andExpect(status().isOk());
	// }
    @Test
	public void DeleteByReviews() throws Exception {;
        Review movie1 = new Review(1, 1, 3, "Bad", "20-11-30");
        List<Review> movies=new ArrayList<Review>();
        movies.add(movie1);
		mvc.perform(MockMvcRequestBuilders.delete("/reviews/{reviewerId}/{reviewId}", 1, 1)
            .contentType(MediaType.APPLICATION_JSON));
            //.andExpect(status().isOk());
	}
    @Test
	public void AddMovies()throws Exception {
		Movies movie1 = new Movies(1, "John wick", 2014, "Action", 180);
		mvc.perform(MockMvcRequestBuilders.post("/movies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmovies.write(movie1).getJson()))
				.andExpect(status().isOk());
	}
    @Test
	public void canUpdatMovies() throws Exception {
		Movies movie1 = new Movies(1, "John wick", 2014, "Action", 180);
        Movies movie2 = new Movies(2, "Military Action", 2010, "Action", 200);
        Movies movie3 = new Movies(3, "Disaster", 2020, "Thriller", 100);

        List<Movies> movies = new ArrayList<Movies>();
        movies.add(movie1);
        movies.add(movie2); 
        movies.add(movie3);
		Movies updatedBook = new Movies(3, "Disaster", 2020, "Thriller", 100);
		mvc.perform(MockMvcRequestBuilders.put("/movies/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updatedBook)));
				// .andExpect(status().isOk());
	}
    @Test
	public void AddMOvieReviews()throws Exception {
		Review review = new Review(1, 1, 3, "Bad", "20-11-30");
		mvc.perform(MockMvcRequestBuilders.post("/Reviewss")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonreview.write(review).getJson()))
				.andExpect(status().isOk());
	}
    
    @Test
	public void canUpdatReviews() throws Exception {
		Review review1 = new Review(1, 1, 3, "Bad", "20-11-30");
        Review review2 = new Review(1, 1, 3, "Bad", "20-11-30");
        Review review3 = new Review(1, 1, 3, "Bad", "20-11-30");

        List<Review> movies = new ArrayList<Review>();
        movies.add(review1);
        movies.add(review2); 
        movies.add(review3);
		Review review = new Review(1, 1, 3, "Bad", "20-11-30");
		mvc.perform(MockMvcRequestBuilders.put("/Reviewss/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(review)));
				// .andExpect(status().isOk());
	}

    // @Test
    // public void AddMOvieReviews() throws Exception {
    //     Review reviewer = new Review(1,"Sabih");
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     JacksonTester.initFields(this, objectMapper);
    //     mvc.perform(MockMvcRequestBuilders.post("/Reviewss")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(jsonReviewer.write(review).getJson()));
    //             .andExpect(status().isOk());
    // }
    // @Test
	// public void AddMOvieReviews() throws Exception {
	// 	Review movie1 = new Review(1, null, 3, "Good", "absfjb");
    //     Review movie2 = new Review(2, null, 2, "Good", "absfjb");
    //     Review movie3 = new Review(3, null, 4, "Good", "absfjb");

    //     List<Review> movies = new ArrayList<Review>();
    //     movies.add(movie1);
    //     movies.add(movie2); 
    //     movies.add(movie3);
	// 	mvc.perform(MockMvcRequestBuilders.put("/Reviewss")
	// 			.contentType(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isOk());
	// }
    
    @Test
    public void contextLoads() {
    }
}
