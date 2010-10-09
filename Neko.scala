package nagoyahackathon.nya

object Neko {
  def solve(in: String) = in.filter{ ch => ch == '2' || ch == '8' }.mkString
}


