package service;

import com.idfinance.service.impl.CoinServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CoinServiceImplTest {
    @Mock
    private CoinServiceImpl coinServiceImpl;

    @Test
    public void shouldReturnList() {
        assertNotNull(coinServiceImpl.getCoinList());
    }
}
