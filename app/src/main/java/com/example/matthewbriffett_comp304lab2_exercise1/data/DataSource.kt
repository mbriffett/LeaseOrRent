package com.example.matthewbriffett_comp304lab2_exercise1.data

import com.example.matthewbriffett_comp304lab2_exercise1.R

class DataSource {
    //for generating unique IDs on DataSource Load
    var ID: Long = 0
    fun generateId(): Long {
        ID++
        return ID
    }
    fun loadApartments(): List<Home> {
        return listOf<Home>(
            Home(generateId(), R.string.Home1, R.drawable.home1, R.string.Price1, R.string.Address1),
            Home(generateId(), R.string.Home2, R.drawable.home2, R.string.Price2, R.string.Address2),
            Home(generateId(), R.string.Home3, R.drawable.home3, R.string.Price3, R.string.Address3),
            Home(generateId(), R.string.Home4, R.drawable.home4, R.string.Price4, R.string.Address4),
            Home(generateId(), R.string.Home5, R.drawable.home5, R.string.Price5, R.string.Address5),
            Home(generateId(), R.string.Home6, R.drawable.home6, R.string.Price6, R.string.Address6),
            Home(generateId(), R.string.Home7, R.drawable.home7, R.string.Price7, R.string.Address7),
            Home(generateId(), R.string.Home8, R.drawable.home8, R.string.Price8, R.string.Address8),
            Home(generateId(), R.string.Home9, R.drawable.home9, R.string.Price9, R.string.Address9),
            Home(generateId(), R.string.Home10,R.drawable.home10,R.string.Price10,R.string.Address10)

        )
    }
    fun loadCondos(): List<Home> {
        return listOf<Home>(
            Home(generateId(), R.string.Home11, R.drawable.home11, R.string.Price11, R.string.Address11),
            Home(generateId(), R.string.Home12, R.drawable.home12, R.string.Price12, R.string.Address12),
            Home(generateId(), R.string.Home13, R.drawable.home13, R.string.Price13, R.string.Address13),
            Home(generateId(), R.string.Home14, R.drawable.home14, R.string.Price14, R.string.Address14),
            Home(generateId(), R.string.Home15, R.drawable.home15, R.string.Price15, R.string.Address15),
            Home(generateId(), R.string.Home16, R.drawable.home16, R.string.Price16, R.string.Address16),
            Home(generateId(), R.string.Home17, R.drawable.home17, R.string.Price17, R.string.Address17),
            Home(generateId(), R.string.Home18, R.drawable.home18, R.string.Price18, R.string.Address18),
            Home(generateId(), R.string.Home19, R.drawable.home19, R.string.Price19, R.string.Address19),
            Home(generateId(), R.string.Home20, R.drawable.home20, R.string.Price20, R.string.Address20)

        )
    }
    fun loadSemiDetachedHomes(): List<Home> {
        return listOf<Home>(
            Home(generateId(), R.string.Home21, R.drawable.home21, R.string.Price21, R.string.Address21),
            Home(generateId(), R.string.Home22, R.drawable.home22, R.string.Price22, R.string.Address22),
            Home(generateId(), R.string.Home23, R.drawable.home23, R.string.Price23, R.string.Address23),
            Home(generateId(), R.string.Home24, R.drawable.home24, R.string.Price24, R.string.Address24),
            Home(generateId(), R.string.Home25, R.drawable.home25, R.string.Price25, R.string.Address25),
            Home(generateId(), R.string.Home26, R.drawable.home26, R.string.Price26, R.string.Address26),
            Home(generateId(), R.string.Home27, R.drawable.home27, R.string.Price27, R.string.Address27),
            Home(generateId(), R.string.Home28, R.drawable.home28, R.string.Price28, R.string.Address28),
            Home(generateId(), R.string.Home29, R.drawable.home29, R.string.Price29, R.string.Address29),
            Home(generateId(), R.string.Home30, R.drawable.home30, R.string.Price30, R.string.Address30)

        )
    }
    fun loadDetachedHomes(): List<Home> {
        return listOf<Home>(
            Home(generateId(),R.string.Home31,R.drawable.home31,R.string.Price31,R.string.Address31),
            Home(generateId(),R.string.Home32,R.drawable.home32,R.string.Price32,R.string.Address32),
            Home(generateId(),R.string.Home33,R.drawable.home33,R.string.Price33,R.string.Address33),
            Home(generateId(),R.string.Home34,R.drawable.home34,R.string.Price34,R.string.Address34),
            Home(generateId(),R.string.Home35,R.drawable.home35,R.string.Price35,R.string.Address35),
            Home(generateId(),R.string.Home36,R.drawable.home36,R.string.Price36,R.string.Address36),
            Home(generateId(),R.string.Home37,R.drawable.home37,R.string.Price37,R.string.Address37),
            Home(generateId(),R.string.Home38,R.drawable.home38,R.string.Price38,R.string.Address38),
            Home(generateId(),R.string.Home39,R.drawable.home39,R.string.Price39,R.string.Address39),
            Home(generateId(),R.string.Home40,R.drawable.home40,R.string.Price40,R.string.Address40)

        )
    }
    fun loadTownHouses(): List<Home> {
        return listOf<Home>(
            Home(generateId(), R.string.Home41, R.drawable.home41, R.string.Price41, R.string.Address41),
            Home(generateId(), R.string.Home42, R.drawable.home42, R.string.Price42, R.string.Address42),
            Home(generateId(), R.string.Home43, R.drawable.home43, R.string.Price43, R.string.Address43),
            Home(generateId(), R.string.Home44, R.drawable.home44, R.string.Price44, R.string.Address44),
            Home(generateId(), R.string.Home45, R.drawable.home45, R.string.Price45, R.string.Address45),
            Home(generateId(), R.string.Home46, R.drawable.home46, R.string.Price46, R.string.Address46),
            Home(generateId(), R.string.Home47, R.drawable.home47, R.string.Price47, R.string.Address47),
            Home(generateId(), R.string.Home48, R.drawable.home48, R.string.Price48, R.string.Address48),
            Home(generateId(), R.string.Home49, R.drawable.home49, R.string.Price49, R.string.Address49),
            Home(generateId(), R.string.Home50, R.drawable.home50, R.string.Price50, R.string.Address50 )

        )
    }
}