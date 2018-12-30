package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        this.allDrivers - this.trips.map { it.driver }

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    if (minTrips == 0)
        return this.allPassengers
    else
        return this.trips.flatMap { trip ->
            trip.passengers.map { passenger ->
                passenger to trip
            }
        }.groupBy { it.first }
                .filter { it.value.size >= minTrips }
                .keys
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.trips.filter { it.driver == driver }
                .flatMap { trip ->
                    trip.passengers.map { passenger ->
                        passenger to trip
                    }
                }
                .groupBy { it.first }
                .mapValues { (_, values) -> values.size }
                .filterValues { it > 1 }
                .keys


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.trips.flatMap { trip ->
            trip.passengers.map { passenger ->
                passenger to scoreDiscount(trip.discount)
            }
        }.groupBy { it.first }
                .mapValues { hasMajorityDiscount(it.value) }
                .filterValues { it }
                .keys

fun scoreDiscount(discount: Double?): Int = if (discount != null) 1 else -1


fun hasMajorityDiscount(passengerDiscounts: List<Pair<Passenger, Int>>): Boolean =
        passengerDiscounts.fold(0) { acc, pd -> acc + pd.second } > 0

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    fun asDurationRange(duration: Int): IntRange {
        val lowerBound = ((duration / 10) * 10)
        val upperBound = lowerBound + 9
        return lowerBound.rangeTo(upperBound)
    }

    fun mostFrequent(ls: List<IntRange>): IntRange? =
            ls.groupBy { it }
                    .mapValues { it.value.size }
                    .maxBy { it.value }?.key

    val durationRanges = this.trips.map { asDurationRange(it.duration) }
    return mostFrequent(durationRanges)
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.allDrivers.size < 5 || this.trips.isEmpty())
        return false

    val driverEarningsDesc = this.trips
            .groupBy { it.driver }
            .values
            .map { it.fold(0.00) { acc, trip -> acc + trip.cost } }
            .sortedDescending()

    val topTwentyPercentDriverCount = (this.allDrivers.size * 0.2).toInt()

    val totalTopTwentyPercentEarnings =
            driverEarningsDesc
                    .take(topTwentyPercentDriverCount)
                    .sum()

    val eightyPercentTotalCost = driverEarningsDesc.sum() * 0.8

    return totalTopTwentyPercentEarnings >= eightyPercentTotalCost
}