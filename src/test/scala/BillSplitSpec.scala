import logic.BillSplit.calculateBillSplit
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class BillSplitSpec extends AnyFunSpec with Matchers with FixtureData {
  describe("Empty Cases") {}
  describe("Non-Empty Cases") {
    it("No Tip or Tax") {
      val result = calculateBillSplit(
        sushiRestaurantDemoBill,
        taxPercentage = None,
        tipPercentage = None
      )

      result.size shouldBe 5
      result(john) shouldBe 28.0
      result(tom) shouldBe 28.0
      result(richard) shouldBe 28.0
      result(harry) shouldBe 29.0
      result(jane) shouldBe 30.0
    }

    it("No Tip and only Tax") {
      val result = calculateBillSplit(
        sushiRestaurantDemoBill,
        taxPercentage = Some(0.08875),
        tipPercentage = None
      )

      result.size shouldBe 5
      result(john) shouldBe 30.49
      result(tom) shouldBe 30.49
      result(richard) shouldBe 30.49
      result(harry) shouldBe 31.57
      result(jane) shouldBe 32.66
    }

    it("No Tax and only Tip") {
      val result = calculateBillSplit(
        sushiRestaurantDemoBill,
        taxPercentage = None,
        tipPercentage = Some(0.18)
      )
      result.size shouldBe 5
      result(john) shouldBe 33.04
      result(tom) shouldBe 33.04
      result(richard) shouldBe 33.04
      result(harry) shouldBe 34.22
      result(jane) shouldBe 35.40
    }

    it("Both Tax and Tip - Tip calculated *BEFORE* Tax") {
      val result = calculateBillSplit(
        sushiRestaurantDemoBill,
        taxPercentage = Some(0.08875),
        tipPercentage = Some(0.18),
        isTipAfterTax = false
      )

      result.size shouldBe 5
      result(john) shouldBe 35.53
      result(tom) shouldBe 35.53
      result(richard) shouldBe 35.53
      result(harry) shouldBe 36.79
      result(jane) shouldBe 38.06
    }

    it("Both Tax and Tip - Tip calculated *AFTER* Tax") {
      val result = calculateBillSplit(
        sushiRestaurantDemoBill,
        taxPercentage = Some(0.08875),
        tipPercentage = Some(0.18)
      )

      result.size shouldBe 5
      result(john) shouldBe 35.97
      result(tom) shouldBe 35.97
      result(richard) shouldBe 35.97
      result(harry) shouldBe 37.26
      result(jane) shouldBe 38.54
    }
  }
  describe("Group Payment") {
    it("Both Tax and Tip - Tip calculated *BEFORE* Tax") {
      val result = calculateBillSplit(
        americanaDemoBill,
        taxPercentage = Some(0.08875),
        tipPercentage = Some(0.20),
        isTipAfterTax = false
      )

      result.size shouldBe 6
      result(john) shouldBe 43.60
      result(tom) shouldBe 82.27
      result(richard) shouldBe 43.60
      result(harry) shouldBe 92.58
      result(jane) shouldBe 43.60
      result(theCouple) shouldBe 86.13
    }
  }
}
