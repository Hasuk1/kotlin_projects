fun main(){
  val x:CircleZone = CircleZone(Pair(1,1),6)
  val y:Incident = Incident(2,2, "Umer ded","89898989",IncidentType.GAS_LEAK)
  if(x.isIncidentInside(y)){
    println("Umer v kruge\n${y.description}\n${y.type}\n${x.getInfo()}")
  } else {
    println("Umer za krugom")
  }
}
