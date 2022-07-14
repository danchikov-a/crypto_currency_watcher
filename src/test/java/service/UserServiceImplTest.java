package service;

import com.idfinance.dto.UserDto;
import com.idfinance.exception.WrongPostRequestException;
import com.idfinance.model.Coin;
import com.idfinance.repository.CoinRepository;
import com.idfinance.repository.UserRepository;
import com.idfinance.service.UserService;
import com.idfinance.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CoinRepository coinRepository;

    @Test(expected = WrongPostRequestException.class)
    public void shouldThrowWrongPostRequestExceptionWhenUnknown() {
        userService = new UserServiceImpl(userRepository, coinRepository);
        userService.saveUser(new UserDto("qwe", "qwe"));
    }

    @Test()
    public void shouldSaveUserWhenAllFieldsAreCorrect() {
        userService = new UserServiceImpl(userRepository, coinRepository);
        when(userRepository.existsByUserNameAndCoinSymbol(anyString(), anyString())).thenReturn(false);
        when(coinRepository.existsBySymbol(anyString())).thenReturn(true);
        when(coinRepository.getCoinBySymbol(anyString())).thenReturn(new Coin());
        UserDto userDto = new UserDto("qwe", "qwe");
        userService.saveUser(userDto);
    }
}
