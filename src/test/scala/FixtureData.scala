import models.{Individual, Group, Item, Shared}

trait FixtureData {
  val john: Individual = Individual("John")
  val tom: Individual = Individual("Thomas")
  val richard: Individual = Individual("Richard")
  val harry: Individual = Individual("Harry")
  val jane: Individual = Individual("Jane")

  val romeo: Individual = Individual("Romeo")
  val juliet: Individual = Individual("Juliet")
  val theCouple: Group =
    Group(name = "Power Couple", individuals = List(romeo, juliet))

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

  val americanaDemoBill: List[Item] = List(
    Item("Wings", 14.00, Shared),
    Item("Cesar Salad", 16.00, Shared),
    Item("Calamari", 17.00, Shared),
    Item("Americana Burger", 18.00, john),
    Item("Strongbow Cider", 8.00, john),
    Item("Chicken Sandwich", 18.00, richard),
    Item("Mermaid Pilsner", 8.00, richard),
    Item("Chicken Sandwich", 18.00, theCouple),
    Item("Penne Pasta", 26.00, theCouple),
    Item("Juneshine Kombucha", 7.00, theCouple),
    Item("Strongbow Cider", 8.00, theCouple),
    Item("Penne Pasta", 26.00, tom),
    Item("The Big Brooklyn Apple", 15.00, tom),
    Item("Foxy Tanya", 15.00, tom),
    Item("Trout", 35.00, harry),
    Item("Cherry Rum Soda", 16.00, harry),
    Item("Paloma", 13.00, harry),
    Item("Penne Pasta", 26.00, jane)
  )
}
