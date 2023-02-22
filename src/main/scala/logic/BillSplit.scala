package logic

import models.Consumer.isShared
import models.{Consumer, Item}

import scala.math.BigDecimal.RoundingMode

object BillSplit {
  private def totalPct(taxPercentages: Double*): BigDecimal =
    taxPercentages.foldLeft[BigDecimal](BigDecimal("1.0"))((acc, cur) =>
      BigDecimal.valueOf(cur) + acc
    )

  private def calculateTipAndTax(
      groupedTotal: Map[Consumer, BigDecimal],
      taxPercentage: Option[Double],
      tipPercentage: Option[Double],
      isTipAfterTax: Boolean
  ): Map[Consumer, BigDecimal] = (taxPercentage, tipPercentage) match {
    case (Some(taxPct), Some(tipPct)) =>
      if (isTipAfterTax) {
        groupedTotal.view
          .mapValues(_ * totalPct(taxPct))
          .mapValues(_ * totalPct(tipPct))
          .toMap
      } else {
        val totalTax = totalPct(taxPct, tipPct)
        groupedTotal.view
          .mapValues(_ * totalTax)
          .toMap
      }
    case (Some(taxPct), None) =>
      groupedTotal.view
        .mapValues(_ * totalPct(taxPct))
        .toMap
    case (None, Some(tipPct)) =>
      groupedTotal.view
        .mapValues(_ * totalPct(tipPct))
        .toMap
    case (None, None) =>
      groupedTotal
  }

  private def processSharedItems(
      items: Map[Consumer, BigDecimal]
  ): Map[Consumer, BigDecimal] = {
    val (sharedTotal, individualTotal) =
      items.partition(cur => isShared(cur._1))
    if (sharedTotal.nonEmpty) {
      val sharedPerPerson = sharedTotal.head._2 / individualTotal.size
      individualTotal.view.mapValues(_ + sharedPerPerson).toMap
    } else {
      individualTotal
    }
  }

  def calculateBillSplit(
      items: List[Item],
      taxPercentage: Option[Double],
      tipPercentage: Option[Double],
      isTipAfterTax: Boolean = true
  ): Map[Consumer, BigDecimal] = {
    val groupedItems =
      items
        .groupMapReduce(_.consumer)(item => BigDecimal.valueOf(item.itemPrice))(
          _ + _
        )

    val processedSharedItems = processSharedItems(groupedItems)

    calculateTipAndTax(
      processedSharedItems,
      taxPercentage,
      tipPercentage,
      isTipAfterTax
    ).view.mapValues(_.setScale(2, RoundingMode.HALF_UP)).toMap
  }
}
