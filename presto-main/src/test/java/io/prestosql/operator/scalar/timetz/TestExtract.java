/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.operator.scalar.timetz;

import io.prestosql.spi.PrestoException;
import io.prestosql.sql.parser.ParsingException;
import io.prestosql.sql.query.QueryAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestExtract
{
    protected QueryAssertions assertions;

    @BeforeClass
    public void init()
    {
        assertions = new QueryAssertions();
    }

    @AfterClass(alwaysRun = true)
    public void teardown()
    {
        assertions.close();
        assertions = null;
    }

    @Test
    public void testYear()
    {
        for (int i = 0; i <= 12; i++) {
            int precision = i;
            assertThatThrownBy(() -> assertions.expression(format("EXTRACT(YEAR FROM CAST(NULL AS TIME(%s) WITH TIME ZONE))", precision)))
                    .isInstanceOf(PrestoException.class)
                    .hasMessage(format("line 1:26: Cannot extract YEAR from time(%s) with time zone", precision));
        }
    }

    @Test
    public void testMonth()
    {
        for (int i = 0; i <= 12; i++) {
            int precision = i;
            assertThatThrownBy(() -> assertions.expression(format("EXTRACT(MONTH FROM CAST(NULL AS TIME(%s) WITH TIME ZONE))", precision)))
                    .isInstanceOf(PrestoException.class)
                    .hasMessage(format("line 1:27: Cannot extract MONTH from time(%s) with time zone", precision));
        }
    }

    @Test
    public void testDay()
    {
        for (int i = 0; i <= 12; i++) {
            int precision = i;
            assertThatThrownBy(() -> assertions.expression(format("EXTRACT(DAY FROM CAST(NULL AS TIME(%s) WITH TIME ZONE))", precision)))
                    .isInstanceOf(PrestoException.class)
                    .hasMessage(format("line 1:25: Cannot extract DAY from time(%s) with time zone", precision));
        }
    }

    @Test
    public void testHour()
    {
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.1+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.12+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.123+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.1234+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.12345+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.123456+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.1234567+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.12345678+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.123456789+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("EXTRACT(HOUR FROM TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '12'");

        assertThat(assertions.expression("hour(TIME '12:34:56+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.1+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.12+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.123+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.1234+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.12345+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.123456+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '12'");
        assertThat(assertions.expression("hour(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '12'");
    }

    @Test
    public void testMinute()
    {
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.1+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.12+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.123+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.1234+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.12345+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.123456+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.1234567+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.12345678+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.123456789+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("EXTRACT(MINUTE FROM TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '34'");

        assertThat(assertions.expression("minute(TIME '12:34:56+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.1+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.12+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.123+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.1234+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.12345+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.123456+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '34'");
        assertThat(assertions.expression("minute(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '34'");
    }

    @Test
    public void testSecond()
    {
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.1+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.12+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.123+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.1234+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.12345+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.123456+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.1234567+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.12345678+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.123456789+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("EXTRACT(SECOND FROM TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '56'");

        assertThat(assertions.expression("second(TIME '12:34:56+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.1+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.12+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.123+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.1234+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.12345+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.123456+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '56'");
        assertThat(assertions.expression("second(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '56'");
    }

    @Test
    public void testMillisecond()
    {
        assertThatThrownBy(() -> assertions.expression("EXTRACT(MILLISECOND FROM TIME '12:34:56+08:35')"))
                .isInstanceOf(ParsingException.class)
                .hasMessage("line 1:8: Invalid EXTRACT field: MILLISECOND");

        assertThat(assertions.expression("millisecond(TIME '12:34:56+08:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.1+08:35')")).matches("BIGINT '100'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.12+08:35')")).matches("BIGINT '120'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.123+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.1234+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.12345+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.123456+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '123'");
        assertThat(assertions.expression("millisecond(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '123'");
    }

    @Test
    public void testTimeZoneHour()
    {
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '8'");

        assertThat(assertions.expression("timezone_hour(TIME '12:34:56+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '8'");

        // negative offsets
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567890-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678901-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789012-08:35')")).matches("BIGINT '-8'");

        assertThat(assertions.expression("timezone_hour(TIME '12:34:56-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567890-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678901-08:35')")).matches("BIGINT '-8'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789012-08:35')")).matches("BIGINT '-8'");

        // negative offset minutes
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.1234567890-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.12345678901-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_HOUR FROM TIME '12:34:56.123456789012-00:35')")).matches("BIGINT '0'");

        assertThat(assertions.expression("timezone_hour(TIME '12:34:56-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.1234567890-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.12345678901-00:35')")).matches("BIGINT '0'");
        assertThat(assertions.expression("timezone_hour(TIME '12:34:56.123456789012-00:35')")).matches("BIGINT '0'");
    }

    @Test
    public void testTimeZoneMinute()
    {
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '35'");

        assertThat(assertions.expression("timezone_minute(TIME '12:34:56+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567890+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678901+08:35')")).matches("BIGINT '35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789012+08:35')")).matches("BIGINT '35'");

        // negative offsets
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567890-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678901-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789012-08:35')")).matches("BIGINT '-35'");

        assertThat(assertions.expression("timezone_minute(TIME '12:34:56-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567890-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678901-08:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789012-08:35')")).matches("BIGINT '-35'");

        // negative offset minutes
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.1234567890-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.12345678901-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("EXTRACT(TIMEZONE_MINUTE FROM TIME '12:34:56.123456789012-00:35')")).matches("BIGINT '-35'");

        assertThat(assertions.expression("timezone_minute(TIME '12:34:56-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.1234567890-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.12345678901-00:35')")).matches("BIGINT '-35'");
        assertThat(assertions.expression("timezone_minute(TIME '12:34:56.123456789012-00:35')")).matches("BIGINT '-35'");
    }
}
