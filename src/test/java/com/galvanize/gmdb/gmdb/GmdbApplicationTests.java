package com.galvanize.gmdb.gmdb;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
    public void getAllMovies() throws Exception 
    {
        Movies movies1=new Movies(1L, "John Wick", 2012, "GOod", 90, null);
        Movies movies2=new Movies(1L, "John Wick", 2012, "GOod", 90, null);
        Movies movies3=new Movies(1L, "John Wick", 2012, "GOod", 90, null);
        

        List<Movies> movies = new ArrayList<>();
        movies.add(movies1);
        movies.add(movies2); 
        movies.add(movies3);

        when(repo.findAll()).thenReturn(movies);
        mvc.perform(get("/movies/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void showReviews() throws Exception {
        Movies movie = new Movies(1L, "John Wick", 2012, "Good", 90, null);
        Review review = new Review(1, "Not Good", "20-20-11", movie);

        when(repo.findById(1L)).thenReturn(Optional.of(review.getMovie()));
        mvc.perform(get("/movies/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());    
    }

    @Test
    public void getAllUser() throws Exception {
        Reviewer reviewer1 = new Reviewer(1, "Ali", "1-March-2023", 5);
        Reviewer reviewer2 = new Reviewer(2, "Fatima", "1-March-2023", 2);
        Reviewer reviewer3 = new Reviewer(3, "Sabih", "1-March-2023", 5);

        List<Reviewer> reviewers = new ArrayList<>();
        reviewers.add(reviewer1);
        reviewers.add(reviewer2);
        reviewers.add(reviewer3);

        when(repo1.findAll()).thenReturn(reviewers);
        mvc.perform(get("/reviewers/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
	public void CreateReviewer()throws Exception {
        Reviewer reviewer1 = new Reviewer(1, "Ali", "1-March-2023", 5);
        Reviewer reviewer2 = new Reviewer(2, "Fatima", "1-March-2023", 2);
        Reviewer reviewer3 = new Reviewer(3, "Sabih", "1-March-2023", 5);        
		mvc.perform(MockMvcRequestBuilders.post("/reviewers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonReviewer.write(reviewer1).getJson())
                .content(jsonReviewer.write(reviewer2).getJson())
                .content(jsonReviewer.write(reviewer3).getJson()))
				.andExpect(status().isOk());
	}

    @Test
    public void testCreateReviewer() throws Exception {
        
        Reviewer reviewer1 = new Reviewer(1,"Sabih", "1-MArch-2023", 5);
        Reviewer reviewer2 = new Reviewer(2,"Ali Bin Ajez", "3-MArch-2023", 2);

        mvc.perform(MockMvcRequestBuilders.post("/reviewers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonReviewer.write(reviewer1).getJson())
                .content(jsonReviewer.write(reviewer2).getJson()))
				.andExpect(status().isOk());
    }   
    
    @Test
    public void canDeleteMovies() throws Exception {
        Movies movies1 = new Movies(1L, "John Wick", 2012, "GOod", 90, null);
        List<Movies> movies = new ArrayList<>();
        movies.add(movies1);
        mvc.perform(MockMvcRequestBuilders.delete("/movies/9")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    

    @Test
	public void DeleteByReviewer() throws Exception {
        
        Reviewer reviewer1 = new Reviewer(1,"Ali","1-March-2023",5);
        List<Reviewer> reviews=new ArrayList<Reviewer>();
        reviews.add(reviewer1);
		mvc.perform(MockMvcRequestBuilders.delete("/reviewers/3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
	}

    @Test
	public void AddMovies()throws Exception {
		Movies movies1=new Movies(1L, "John Wick", 2012, "GOod", 90, null);
		mvc.perform(MockMvcRequestBuilders.post("/movies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmovies.write(movies1).getJson()))
				.andExpect(status().isOk());
	}

    @Test
	public void canUpdatMovies() throws Exception {
		Movies movie1 = new Movies(1L, "John wick", 2014, "Action", 180,null);
		mvc.perform(MockMvcRequestBuilders.put("/movies/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmovies.write(movie1).getJson()))
				.andExpect(status().isOk());
	}
    
    @Test
    public void addMovieReviews() throws Exception {
        Movies movie2 = new Movies(1L, "John wick", 2014, "Action", 180,null);
        Review review1 = new Review(1, "Bad Movie", "20-11-2011", movie2);
        Movies movie3 = new Movies(1L, "John wick", 2014, "Action", 180,null);
        Review review2 = new Review(1, "Good Movie", "20-11-2011", movie3);
        List<Review> reviews= new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        Movies movie1 = new Movies(1L, "John wick", 2014, "Action", 180,reviews);
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonmovies.write(movie1).getJson()))
                .andExpect(status().isOk());
    }
    @Test
    public void canUpdateReviews() throws Exception {
        // Test setup...
        Review review1 = new Review( 1, "Good", "11-2-1998", null);
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonreview.write(review1).getJson()))
                .andExpect(status().isOk());
    }
    
    // @Test
	// public void canUpdatReviews() throws Exception {
	// 	Review review1 = new Review(1, 1, "Good", "11-2-1998", null);
	// 	mvc.perform(MockMvcRequestBuilders.put("/Reviewss/update")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(jsonreview.write(review1).getJson()))
	// 			.andExpect(status().isOk());
	// }

    // 11. As an admin
    //    I can impersonate a reviewer and do any of the things they can do
    //    so that I can help confused reviewers.
    @Test
    public void contextLoads() {
    }
}
