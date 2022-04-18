import scala.io.{BufferedSource, Source}
import io.circe.generic.auto._
import io.circe.parser.decode
import io.circe.syntax._
import scala.collection.immutable.ListMap


import scala.language.implicitConversions

object Parsing extends App {

  case class Country(
                      region: Option[String],
                      area: Double,
                      capital:List[String],
                      name: Name
                    )

  case class Name(official: String)

  case class TargetCountry(
                            name: String,
                            capital: String,
                            area: Double
                          )
  val source = Source.fromFile("src/main/resources/countries.json").mkString

  val value = decode[List[Country]](source)
 val result = value match{
    case Right(value) =>
      value
        .filter(x => x.region.contains("Africa") && x.capital.nonEmpty)
        .sortBy(_.area)(Ordering[Double].reverse)
        .slice(0,10)
        .map(x => TargetCountry(
            name = x.name.official,
            capital = x.capital.head,
            area = x.area
          )
        ).asJson.noSpaces
    case Left(value) => throw new RuntimeException(s"Parsing error $value")
  }
  println(result)
}
