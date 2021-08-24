package com.igorkunicyn.minimarket;

import com.igorkunicyn.minimarket.services.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest
//@WebMvcTest(CartController.class)
//@ContextConfiguration(classes = {MyConfig.class, SecurityConfig.class})
//@WebAppConfiguration
@AutoConfigureMockMvc
public class CartMockTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;
    

//    @Before
//    public void setUp(){
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
////                .dispatchOptions(true)
//                .build();
//    }

    @Test
    @WithMockUser
    public void addToCart() throws Exception {
        mockMvc.perform(get("/cart/add/{id}",1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    @WithMockUser
    public void cartPage() throws Exception {
        mockMvc.perform(get("http://localhost/cart"))
                .andExpect(view().name("cart-page"))
                .andExpect(model().attributeExists("price"))
                .andExpect(model().attributeExists("cartList"));
    }



}
