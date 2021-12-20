package com.carriganholt.yourstattracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GameActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val twoPointMadeMinus = findViewById<Button>(R.id.gameTwoPointersMadeMinus)
        val twoPointMadeShow = findViewById<TextView>(R.id.gameTwoPointersMadeShow)
        val twoPointMadePlus = findViewById<Button>(R.id.gameTwoPointersMadePlus)
        val twoPointMissedMinus = findViewById<Button>(R.id.gameTwoPointersMissedMinus)
        val twoPointMissedShow = findViewById<TextView>(R.id.gameTwoPointersMissedShow)
        val twoPointMissedPlus = findViewById<Button>(R.id.gameTwoPointersMissedPlus)
        val threePointMadeMinus = findViewById<Button>(R.id.gameThreePointersMadeMinus)
        val threePointMadeShow = findViewById<TextView>(R.id.gameThreePointersMadeShow)
        val threePointMadePlus = findViewById<Button>(R.id.gameThreePointersMadePlus)
        val threePointMissedMinus = findViewById<Button>(R.id.gameThreePointersMissedMinus)
        val threePointMissedShow = findViewById<TextView>(R.id.gameThreePointersMissedShow)
        val threePointMissedPlus = findViewById<Button>(R.id.gameThreePointersMissedPlus)
        val freeThrowMadeMinus = findViewById<Button>(R.id.gameFreeThrowsMadeMinus)
        val freeThrowMadeShow = findViewById<TextView>(R.id.gameFreeThrowsMadeShow)
        val freeThrowMadePlus = findViewById<Button>(R.id.gameFreeThrowsMadePlus)
        val freeThrowMissedMinus = findViewById<Button>(R.id.gameFreeThrowsMissedMinus)
        val freeThrowMissedShow = findViewById<TextView>(R.id.gameFreeThrowsMissedShow)
        val freeThrowMissedPlus = findViewById<Button>(R.id.gameFreeThrowsMissedPlus)
        val reboundMinus = findViewById<Button>(R.id.reboundMinus)
        val reboundShow = findViewById<TextView>(R.id.reboundShow)
        val reboundPlus = findViewById<Button>(R.id.reboundPlus)
        val assistMinus = findViewById<Button>(R.id.assistMinus)
        val assistShow = findViewById<TextView>(R.id.assistShow)
        val assistPlus = findViewById<Button>(R.id.assistPlus)
        val stealMinus = findViewById<Button>(R.id.stealMinus)
        val stealShow = findViewById<TextView>(R.id.stealShow)
        val stealPlus = findViewById<Button>(R.id.stealPlus)
        val blockMinus = findViewById<Button>(R.id.blockMinus)
        val blockShow = findViewById<TextView>(R.id.blockShow)
        val blockPlus = findViewById<Button>(R.id.blockPlus)
        val endGame = findViewById<Button>(R.id.endGame)

        var twoPointMade = 0.0
        var twoPointMissed = 0.0
        var threePointMade = 0.0
        var threePointMissed = 0.0
        var freeThrowMade = 0.0
        var freeThrowMissed = 0.0
        var rebounds = 0.0
        var assists = 0.0
        var steals = 0.0
        var blocks = 0.0

        twoPointMadeMinus.setOnClickListener {
            if(twoPointMade != 0.0) {
                twoPointMade--
            }
            twoPointMadeShow.text = twoPointMade.toInt().toString()
        }

        twoPointMadePlus.setOnClickListener {
            twoPointMade++
            twoPointMadeShow.text = twoPointMade.toInt().toString()
        }

        twoPointMissedMinus.setOnClickListener {
            if(twoPointMissed != 0.0) {
                twoPointMissed--
            }
            twoPointMissedShow.text = twoPointMissed.toInt().toString()
        }

        twoPointMissedPlus.setOnClickListener {
            twoPointMissed++
            twoPointMissedShow.text = twoPointMissed.toInt().toString()
        }

        threePointMadeMinus.setOnClickListener {
            if(threePointMade != 0.0) {
                threePointMade--
            }
            threePointMadeShow.text = threePointMade.toInt().toString()
        }

        threePointMadePlus.setOnClickListener {
            threePointMade++
            threePointMadeShow.text = threePointMade.toInt().toString()
        }

        threePointMissedMinus.setOnClickListener {
            if(threePointMissed != 0.0) {
                threePointMissed--
            }
            threePointMissedShow.text = threePointMissed.toInt().toString()
        }

        threePointMissedPlus.setOnClickListener {
            threePointMissed++
            threePointMissedShow.text = threePointMissed.toInt().toString()
        }

        freeThrowMadeMinus.setOnClickListener {
            if(freeThrowMade != 0.0) {
                freeThrowMade--
            }
            freeThrowMadeShow.text = freeThrowMade.toInt().toString()
        }

        freeThrowMadePlus.setOnClickListener {
            freeThrowMade++
            freeThrowMadeShow.text = freeThrowMade.toInt().toString()
        }

        freeThrowMissedMinus.setOnClickListener {
            if(freeThrowMissed != 0.0) {
                freeThrowMissed--
            }
            freeThrowMissedShow.text = freeThrowMissed.toInt().toString()
        }

        freeThrowMissedPlus.setOnClickListener {
            freeThrowMissed++
            freeThrowMissedShow.text = freeThrowMissed.toInt().toString()
        }

        reboundMinus.setOnClickListener {
            if(rebounds != 0.0) {
                rebounds--
            }
            reboundShow.text = rebounds.toInt().toString()
        }

        reboundPlus.setOnClickListener {
            rebounds++
            reboundShow.text = rebounds.toInt().toString()
        }

        assistMinus.setOnClickListener {
            if(assists != 0.0) {
                assists--
            }
            assistShow.text = assists.toInt().toString()
        }

        assistPlus.setOnClickListener {
            assists++
            assistShow.text = assists.toInt().toString()
        }

        stealMinus.setOnClickListener {
            if(steals != 0.0) {
                steals--
            }
            stealShow.text = steals.toInt().toString()
        }

        stealPlus.setOnClickListener {
            steals++
            stealShow.text = steals.toInt().toString()
        }

        blockMinus.setOnClickListener {
            if(blocks != 0.0) {
                blocks--
            }
            blockShow.text = blocks.toInt().toString()
        }

        blockPlus.setOnClickListener {
            blocks++
            blockShow.text = blocks.toInt().toString()
        }

        endGame.setOnClickListener {
            AlertDialog.Builder(this)
            .setTitle("End game")
            .setMessage("Do you want to end the game?")
            .setPositiveButton("Yes") { _, _ ->

                val shotsMissed = twoPointMissed + threePointMissed
                val shotsMade = twoPointMade + threePointMade

                val db = FirebaseFirestore.getInstance()
                db.collection("stats").get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (i in task.result!!) {
                            if (i["id"].toString() == auth.currentUser!!.uid) {
                                val totalGames = i["games"].toString().toDouble() + 1.0
                                val totalShots = i["shots"].toString().toDouble() + shotsMade + shotsMissed
                                val totalShotsMade = i["shotsMade"].toString().toDouble() + shotsMade
                                val totalFreeThrows = i["freeThrows"].toString().toDouble() + freeThrowMissed + freeThrowMade
                                val totalFreeThrowsMade = i["freeThrowsMade"].toString().toDouble() + freeThrowMade
                                val totalThreePoints = i["threePoints"].toString().toDouble() + threePointMade + threePointMissed
                                val totalThreePointsMade = i["threePointsMade"].toString().toDouble() + threePointMade
                                val totalPoints = i["points"].toString().toDouble() + (twoPointMade * 2) + (threePointMade * 3) + freeThrowMade
                                val totalRebounds = i["rebounds"].toString().toDouble() + rebounds
                                val totalAssists = i["assists"].toString().toDouble() + assists
                                val totalSteals = i["steals"].toString().toDouble() + steals
                                val totalBlocks = i["blocks"].toString().toDouble() + blocks

                                val stat: MutableMap<String, Any> = HashMap()
                                stat["games"] = totalGames
                                stat["shots"] = totalShots
                                stat["shotsMade"] = totalShotsMade
                                stat["freeThrows"] = totalFreeThrows
                                stat["freeThrowsMade"] = totalFreeThrowsMade
                                stat["threePoints"] = totalThreePoints
                                stat["threePointsMade"] = totalThreePointsMade
                                stat["points"] = totalPoints
                                stat["rebounds"] = totalRebounds
                                stat["assists"] = totalAssists
                                stat["steals"] = totalSteals
                                stat["blocks"] = totalBlocks
                                stat["id"] = auth.currentUser!!.uid
                                db.collection("stats").document(auth.currentUser!!.uid).update(stat)

                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("games", totalGames)
                                intent.putExtra("shots", totalShots)
                                intent.putExtra("shotsMade", totalShotsMade)
                                intent.putExtra("freeThrows", totalFreeThrows)
                                intent.putExtra("freeThrowsMade", totalFreeThrowsMade)
                                intent.putExtra("threePoints", totalThreePoints)
                                intent.putExtra("threePointsMade", totalThreePointsMade)
                                intent.putExtra("points", totalPoints)
                                intent.putExtra("rebounds", totalRebounds)
                                intent.putExtra("assists", totalAssists)
                                intent.putExtra("steals", totalSteals)
                                intent.putExtra("blocks", totalBlocks)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
            .setNegativeButton("No", null).show()
        }
    }

    override fun onBackPressed() {}
}