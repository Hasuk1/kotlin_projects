open class Zone() {
  private val kPhoneNumber = "88008473824"
  open fun isIncidentInside(incident: Incident?): Boolean {
    return false
  }

  open fun getPhoneNumber(): String {
    return kPhoneNumber
  }
}