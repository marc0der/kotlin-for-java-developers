package taxipark

import org.junit.Assert
import org.junit.Test

class TestTaxiPark {
    @Test
    fun testFakeDrivers() {
        val tp = taxiPark(1..3, 1..2,
                trip(1, 1),
                trip(1, 2))
        Assert.assertEquals("Wrong result for 'findFakeDrivers()'." + tp.display(),
                drivers(2, 3).toSet(), tp.findFakeDrivers())
    }

    @Test
    fun testFaithfulPassengers() {
        val tp = taxiPark(1..3, 1..5,
                trip(1, 1),
                trip(2, 1),
                trip(1, 4),
                trip(3, 4),
                trip(1, 5),
                trip(2, 5),
                trip(2, 2))
        Assert.assertEquals("Wrong result for 'findFaithfulPassengers()'. MinTrips: 2." + tp.display(),
                passengers(1, 4, 5), tp.findFaithfulPassengers(2))
    }

    @Test
    fun testFaithfulPassengersZeroTrips() {
        val tp = taxiPark(0..3, 0..11,
                trip(0, 7, 16, 34.0, 0.2),
                trip(0, 7, 7, 14.0),
                trip(0, 8, 7, 14.0),
                trip(0, 11, 4, 1.0, 0.4),
                trip(0, 7, 4, 1.0, 0.4),
                trip(0, 5, 4, 1.0, 0.4),
                trip(0, 8, 4, 1.0, 0.4),
                trip(0, 11, 15, 2.0),
                trip(0, 4, 15, 2.0),
                trip(0, 6, 15, 2.0),

                trip(1, 4, 0, 3.0),
                trip(1, 6, 0, 3.0),
                trip(1, 3, 0, 3.0),
                trip(1, 3, 18, 19.0),
                trip(1, 4, 18, 19.0),
                trip(1, 3, 2, 34.0, 0.2),
                trip(1, 4, 2, 34.0, 0.2),
                trip(1, 5, 2, 34.0, 0.2),
                trip(1, 4, 5, 31.0, 0.1),
                trip(1, 8, 5, 31.0, 0.1),
                trip(1, 7, 5, 31.0, 0.1),
                trip(1, 5, 0, 15.0, 0.4),
                trip(1, 7, 0, 15.0, 0.4),

                trip(2, 9, 19, 16.0),
                trip(2, 1, 23, 33.0),
                trip(2, 6, 23, 33.0),

                trip(3, 6, 33, 10.0),
                trip(3, 9, 4, 26.0, 0.3),
                trip(3, 11, 33, 10.0),
                trip(3, 11, 20, 22.0),
                trip(3, 9, 20, 22.0),
                trip(3, 7, 0, 31.0, 0.3),
                trip(3, 4, 0, 31.0, 0.3),
                trip(3, 4, 35, 2.0),
                trip(3, 8, 35, 2.0),
                trip(3, 1, 35, 2.0),
                trip(3, 1, 35, 30.0),
                trip(3, 6, 38, 9.0),
                trip(3, 7, 38, 9.0),
                trip(3, 9, 24, 17.0),
                trip(3, 8, 24, 17.0),
                trip(3, 6, 24, 17.0),
                trip(3, 0, 37, 3.0, 0.1))
        Assert.assertEquals("Wrong result for 'findFaithfulPassengers()'. MinTrips: 0." + tp.display(),
                passengers(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), tp.findFaithfulPassengers(0))

    }

    @Test
    fun testFrequentPassengers() {
        val tp = taxiPark(1..2, 1..4,
                trip(1, 1),
                trip(1, 1), trip(1, listOf(1, 3)),
                trip(1, 3),
                trip(1, 2),
                trip(2, 2))
        Assert.assertEquals("Wrong result for 'findFrequentPassengers()'. Driver: ${driver(1).name}." + tp.display(),
                passengers(1, 3), tp.findFrequentPassengers(driver(1)))
    }

    @Test
    fun testSmartPassengers() {
        val tp = taxiPark(1..2, 1..2,
                trip(1, 1, discount = 0.1),
                trip(2, 2))
        Assert.assertEquals("Wrong result for 'findSmartPassengers()'." + tp.display(),
                passengers(1), tp.findSmartPassengers())
    }

    @Test
    fun testTheMostFrequentTripDuration() {
        val tp = taxiPark(1..3, 1..5,
                trip(1, 1, duration = 10),
                trip(3, 4, duration = 30),
                trip(1, 2, duration = 20),
                trip(2, 3, duration = 35))
        // The period 30..39 is the most frequent since there are two trips (duration 30 and 35)
        Assert.assertEquals("Wrong result for 'findTheMostFrequentTripDurationPeriod()'.",
                30..39, tp.findTheMostFrequentTripDurationPeriod())
    }

    @Test
    fun testParetoPrincipleSucceeds() {
        val tp = taxiPark(1..5, 1..4,
                trip(1, 1, duration = 20, distance = 20.0),
                trip(1, 2, duration = 20, distance = 20.0),
                trip(1, 3, duration = 20, distance = 20.0),
                trip(1, 4, duration = 20, distance = 20.0),
                trip(2, 1, duration = 20, distance = 20.0))
        // The income of driver #1: 160.0;
        // the total income of drivers #2..5: 40.0.
        // The first driver constitutes exactly 20% of five drivers
        // and his relative income is 160.0 / 200.0 = 80%.

        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                true, tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleFails() {
        val tp = taxiPark(1..5, 1..4,
                trip(1, 1, duration = 20, distance = 20.0),
                trip(1, 2, duration = 20, distance = 20.0),
                trip(1, 3, duration = 20, distance = 20.0),
                trip(2, 4, duration = 20, distance = 20.0),
                trip(3, 1, duration = 20, distance = 20.0))
        // The income of driver #1: 120.0;
        // the total income of drivers #2..5: 80.0.
        // The first driver constitutes 20% of five drivers
        // but his relative income is 120.0 / 200.0 = 60%
        // which is less than 80%.

        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                false, tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleNoTrips() {
        val tp = taxiPark(1..5, 1..4)
        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                false, tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleAgain() {
        val tp = taxiPark(1..5, 1..4,
                trip(1, 1, duration = 20, distance = 20.0),
                trip(1, 2, duration = 20, distance = 20.0),
                trip(1, 3, duration = 20, distance = 20.0),
                trip(1, 4, duration = 20, distance = 20.0),
                trip(2, 1, duration = 20, distance = 19.0))
        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                true, tp.checkParetoPrinciple())
    }
}