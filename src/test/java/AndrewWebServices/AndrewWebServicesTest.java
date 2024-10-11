package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
//        database = new Database(); // We probably don't want to access our real database...
        // fake
        database = new InMemoryDatabase();
//        recommender = new RecSys();
        // stub
        recommender = mock(RecSys.class);
        when(recommender.getRecommendation("Scotty")).thenReturn("Animal House");

//        promoService = new PromoService();
        // mock
        promoService = mock(PromoService.class);
//        when(promoService.mailTo("email")).thenReturn(true);

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        andrewWebService.sendPromoEmail("email");
        verify(promoService).mailTo("email");
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        andrewWebService.logIn("Scotty", 17214);
        verifyNoMoreInteractions(promoService);
//        verify(promoService, never()).mailTo(anyString());
    }
}
