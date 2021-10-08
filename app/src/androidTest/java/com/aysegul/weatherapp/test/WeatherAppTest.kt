package  com.aysegul.weatherapp.test

import com.aysegul.weatherapp.repo.WeatherDaoRepository
import org.junit.Test
import org.junit.Assert.*

class WeatherAppTest {
    private val wdaor: WeatherDaoRepository

    init {
        wdaor = WeatherDaoRepository()
    }

    @Test
    fun testAssertionQuery() {
        wdaor.getWeatherInfo(33.44, 94.04, "8ddadecc7ae4f56fee73b2b405a63659")
        Thread.sleep(5000)
        var weather = wdaor.weatherInfoTest()
            if (weather != null) {
                assertEquals("Asia/Shanghai", weather.timezone)
                assertEquals("Rain", weather.daily[0].weather[0].main)
           }
    }
}

