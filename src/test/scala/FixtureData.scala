import models.{Individual, Item, Shared}

trait FixtureData {
  val john: Individual = Individual("John")
  val tom: Individual = Individual("Thomas")
  val richard: Individual = Individual("Richard")
  val harry: Individual = Individual("Harry")
  val jane: Individual = Individual("Jane")

  val sushiRestaurantDemoBill: List[Item] = List(
    Item("Spicy Tonkatsu Thick Ramen Noodle", 19.00, richard),
    Item("Spicy Tonkatsu Thick Ramen Noodle", 19.00, john),
    Item("Spicy Tonkatsu Thick Ramen Noodle", 19.00, tom),
    Item("Curry Chicken Katsu", 20.00, harry),
    Item("Salmon Don", 21.00, jane),
    Item("Tokyo Iced Tea", 5.00, john),
    Item("Rum Coke", 5.00, tom),
    Item("Rum Coke", 5.00, richard),
    Item("Long Island Iced Tea", 5.00, jane),
    Item("Long Island Iced Tea", 5.00, harry),
    Item("Sweet Potato Roll", 5.00, Shared),
    Item("Spicy Salmon", 5.00, Shared),
    Item("Spicy Tuna", 5.00, Shared),
    Item("Eel Cucumber", 5.00, Shared)
  )
}
