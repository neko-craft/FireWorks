package net.nekocraft.fireworks

import com.destroystokyo.paper.Title
import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.map.MinecraftFont
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

val fw1 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.AQUA)
        .withFade(Color.YELLOW)
        .with(FireworkEffect.Type.STAR)
        .trail(true)
        .build()

val fw2 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.YELLOW)
        .withColor(Color.RED)
        .withFade(Color.GREEN)
        .withFade(Color.BLUE)
        .with(FireworkEffect.Type.BALL_LARGE)
        .trail(true)
        .build()

val fw3 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.YELLOW)
        .withColor(Color.RED)
        .withFade(Color.GREEN)
        .withFade(Color.BLUE)
        .with(FireworkEffect.Type.STAR)
        .trail(true)
        .build()

val fw4 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.AQUA)
        .withColor(Color.YELLOW)
        .withColor(Color.RED)
        .withFade(Color.GREEN)
        .withFade(Color.BLUE)
        .with(FireworkEffect.Type.STAR)
        .trail(true)
        .build()

val fw5 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.BLUE)
        .withColor(Color.AQUA)
        .withFade(Color.YELLOW)
        .withFade(Color.RED)
        .withFade(Color.GREEN)
        .with(FireworkEffect.Type.STAR)
        .trail(true)
        .build()

val fw6 = FireworkEffect
        .builder()
        .flicker(true)
        .withColor(Color.YELLOW)
        .withColor(Color.RED)
        .withFade(Color.BLUE)
        .withFade(Color.AQUA)
        .withFade(Color.GREEN)
        .with(FireworkEffect.Type.STAR)
        .trail(true)
        .build()

const val end = PI * 2
const val radius = 40
const val step = PI / 18
const val step3 = PI / 32

val r = Random()
fun randomColor() = Color.fromBGR(r.nextInt(255), r.nextInt(255), r.nextInt(255))
fun randomType() = when (r.nextInt(3)) {
    0 -> FireworkEffect.Type.BALL
    1 -> FireworkEffect.Type.BURST
    2 -> FireworkEffect.Type.STAR
    3 -> FireworkEffect.Type.CREEPER
    else -> FireworkEffect.Type.BALL_LARGE
}
fun randomN() = (if (Math.random() > 0.5) 1 else -1) * 0.8

@Suppress("unused", "UNSUPPORTED")
class Main: JavaPlugin(), Listener {
    private var timer: BukkitTask? = null
    private lateinit var world: World

//    private val points = [
//        Location(world, -457.0, 64.0, -8.0),
//        Location(world, -486.0, 65.0, -30.0),
//        Location(world, -487.0, 64.0, -64.0),
//        Location(world, -454.0, 66.0, -82.0),
//        Location(world, -426.0, 70.0, -65.0),
//        Location(world, -432.0, 70.0, -25.0),
//        Location(world, -450.0, 63.0, -5.0)
//    ]
//    private val center0 = Location(world, -460.0, 70.0, -51.0)
//    private val center = center0.clone().add(0.0, 70.0, 0.0)

    private lateinit var points: Array<Location>
    private lateinit var center0: Location
    private lateinit var center: Location

    private val blocks = ArrayList<Location>()

    override fun onEnable() {
        world = server.getWorld("world")!!
        center0 = Location(world, -1288.0, 72.0, -1472.0)
        center = center0.clone().add(0.0, 70.0, 0.0)
        points = [
            Location(world, -1235.0, 65.0, -1471.0),
            Location(world, -1250.0, 65.0, -1493.0),
            Location(world, -1286.0, 74.0, -1515.0),
            Location(world, -1322.0, 74.0, -1490.0),
            Location(world, -1322.0, 77.0, -1457.0),
            Location(world, -1283.0, 65.0, -1436.0),
            Location(world, -1263.0, 63.0, -1442.0)
        ]

        var i = 0.0
        while (i < end) {
            blocks.add(center.clone().add(cos(i) * radius, 0.0, sin(i) * radius))
            i += step
        }

//        server.onlinePlayers.forEach { if (it.isOp) it.sendMessage("Reloaded!") }
        server.pluginManager.registerEvents(this, this)
        val cmd = server.getPluginCommand("fireworksstart")!!
//        val field = cmd::class.java.getDeclaredField("owningPlugin")
//        field.isAccessible = true
//        field.set(cmd, this)
        cmd.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp || sender !is Player) return true
        world.players.forEach { it.stopSound("net.nekocraft.hny", SoundCategory.RECORDS) }
        world.time = 14000

        async {
            title(Title("§eNekoCraft §7- §e除夕之夜", "§bQQ群: §a7923309", 10, 40, 10))
            Thread.sleep(3000)
            title(Title("§bPresent By: §eShirasawa", "§a即将为您呈现...", 10, 40, 10))
            Thread.sleep(3000)
            title(Title("§e注意事项", "§b节目开始后请不要随意走动", 10, 40, 10))
            Thread.sleep(3000)
            title(Title("§a让我们一起倒数...", "", 10, 40, 10))
            Thread.sleep(3000)
            title(Title("§b5!"))
            Thread.sleep(1000)
            title(Title("§b4!"))
            Thread.sleep(1000)
            title(Title("§b3!"))
            Thread.sleep(1000)
            title(Title("§b2!"))
            Thread.sleep(1000)
            title(Title("§b1!"))
            Thread.sleep(1000)
            title(Title("§a开始!"))
            Thread.sleep(3000)
            world.players.forEach {
                it.playSound(it.location, "net.nekocraft.hny", SoundCategory.RECORDS, 1f, 1f)
            }
            Thread.sleep(6000)
            points.forEach { p ->
                sync {
                    repeat(12) {
                        genFW(f(p), fw1)
                    }
                }
                Thread.sleep(1500)
            }
            Thread.sleep(5000)
            sync {
                points.forEach { p ->
                    repeat(14) {
                        genFW(f(p), fw2)
                    }
                }
            }
            Thread.sleep(2000)
            sync {
                points.forEach { p ->
                    repeat(14) {
                        genFW(f(p, 10), fw3)
                    }
                }
            }
            Thread.sleep(4000)
            points.forEach { p ->
                Thread.sleep(1000)
                sync {
                    repeat(26) {
                        genFW(f(p, 10),
                                FireworkEffect
                                        .builder()
                                        .flicker(true)
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .with(FireworkEffect.Type.BURST)
                                        .build())
                    }
                }
            }
            Thread.sleep(3000)
            sync {
                points.forEach { p ->
                    repeat(18) {
                        genFW(f(p, 25), FireworkEffect
                                .builder()
                                .flicker(true)
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .with(randomType())
                                .build())
                    }
                }
            }
            Thread.sleep(2000)
            sync {
                points.forEach { p ->
                    repeat(18) {
                        genFW(f(p, 25), FireworkEffect
                                .builder()
                                .flicker(true)
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .with(randomType())
                                .build())
                    }
                }
            }
            Thread.sleep(3000)
            // 10s
            var l2 = center0.clone()
            repeat(70) {
                Thread.sleep(100)
                l2.y++
                world.spawnParticle(Particle.FIREWORKS_SPARK, l2, 25, 0.0, 0.0, 0.0, 1.0, null, true)
            }
            sync {
                points.forEach { p ->
                    repeat(18) {
                        genFW(f(p, 25), FireworkEffect
                                .builder()
                                .flicker(true)
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .with(randomType())
                                .build())
                    }
                }
            }
            Thread.sleep(5000)
            sync {
                points.forEach { p ->
                    repeat(22) {
                        genFW(f(p, 25), FireworkEffect
                                .builder()
                                .flicker(true)
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withColor(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .withFade(randomColor())
                                .with(randomType())
                                .build())
                    }
                }
            }
            Thread.sleep(2500)
            rain(Particle.ENCHANTMENT_TABLE)
            Thread.sleep(2500)
            repeat(15) {
                sync {
                    points.forEach { p ->
                        repeat(5) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                }
                draw(center, 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                Thread.sleep(500)
            }
            // 5s
            l2 = center0.clone()
            repeat(70) {
                Thread.sleep(72)
                l2.y++
                world.spawnParticle(Particle.FIREWORKS_SPARK, l2, 25, 0.0, 0.0, 0.0, 1.0, null, true)
            }
            repeat(20) {
                sync {
                    points.forEach { p ->
                        repeat(7) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                }
                draw(center, 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                Thread.sleep(500)
            }

            Thread.sleep(5000)
            repeat(15) {
                sync {
                    points.forEach { p ->
                        repeat(7) {
                            genFW(f(p, 8), fw4)
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .trail(true)
                                    .build())
                        }
                    }
                }
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.SPELL_WITCH, 0)
                draw(center, 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(70.0, 0.0, 70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(-70.0, 0.0, -70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                Thread.sleep(500)
            }
            Thread.sleep(2500)
            rain(Particle.DRIP_LAVA)
            Thread.sleep(2500)
            repeat(6) {
                sync {
                    points.forEach { p ->
                        repeat(7) {
                            genFW(f(p, 8), fw4)
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .trail(true)
                                    .build())
                        }
                    }
                }
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                draw(center, 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(70.0, 0.0, 70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(-70.0, 0.0, -70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                Thread.sleep(1000)
            }

            Thread.sleep(2000)
            async {
                Thread.sleep(4000)
                var t = 0.0
                while (t < 15) {
                    val x = t * cos(10 + t * 1800) * 5
                    val z = t * sin(10 + t * 1800) * 5
                    world.spawnParticle(Particle.CLOUD, center.clone().add(x, -60.0 + t * 5, z), 0, 0.0, 0.0, 0.0, 1.0, null, true)
                    t += 0.003
                    Thread.sleep(2)
                }
            }
            repeat(10) {
                sync {
                    points.forEach { p ->
                        repeat(7) {
                            genFW(f(p, 8), fw4)
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .trail(true)
                                    .build())
                        }
                    }
                }
                draw(center, 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                scatter(r.nextInt(2) + 1, PI * 2 / 40, Particle.FIREWORKS_SPARK, 0)
                draw(center.clone().add(-70.0, 0.0, 70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(70.0, 0.0, -70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(70.0, 0.0, 70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                draw(center.clone().add(-70.0, 0.0, -70.0), 10 + it * 2, PI * 2 / (40 + it), Particle.FLAME)
                Thread.sleep(1500)
            }
            Thread.sleep(3000)

            repeat(20) {
                sync {
                    points.forEach { p ->
                        repeat(10) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                    var i = 0.0
                    while (i < end) {
                        genFW(center0.clone().add(cos(i) * radius, 10.0, sin(i) * radius), fw5, 2)
                        i += step3
                    }
                }
                Thread.sleep(600)
            }
            Thread.sleep(3000)
            repeat(20) {
                sync {
                    points.forEach { p ->
                        repeat(12) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                    var i = 0.0
                    while (i < end) {
                        genFW(center0.clone().add(cos(i) * radius, 10.0, sin(i) * radius), fw6, 2)
                        i += step3
                    }
                }
                Thread.sleep(600)
            }

            Thread.sleep(1500)
            rain(Particle.FIREWORKS_SPARK)
            Thread.sleep(1500)

            repeat(20) {
                sync {
                    points.forEach { p ->
                        repeat(12) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                    var i = 0.0
                    while (i < end) {
                        genFW(center0.clone().add(cos(i) * radius, 10.0, sin(i) * radius), fw5, 2)
                        i += step3
                    }
                }
                Thread.sleep(600)
            }
            Thread.sleep(3000)
            rain(Particle.DRIP_WATER)
            Thread.sleep(1000)
            repeat(32) {
                genHeart(center.clone().add(r.nextInt(120) - 60.0, -40.0, r.nextInt(120) - 60.0))
                genHeart(center.clone().add(r.nextInt(120) - 60.0, -40.0, r.nextInt(120) - 60.0))
                Thread.sleep(250)
            }
            Thread.sleep(3000)
            async {
                Thread.sleep(8000)
                drawText("NekoCraft")
                Thread.sleep(4000)
                drawText("is Wonderful")
                Thread.sleep(4000)
                drawText("Because")
                Thread.sleep(5000)
                drawText("of You !")
            }
            repeat(20) {
                sync {
                    points.forEach { p ->
                        repeat(4) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                    var i = 0.0
                    while (i < end) {
                        genFW(center0.clone().add(cos(i) * radius, 10.0, sin(i) * radius), fw6, 2)
                        i += step3
                    }
                }
                Thread.sleep(600)
            }
            Thread.sleep(2000)
            repeat(20) {
                world.spawnParticle(Particle.DRAGON_BREATH, center, 200, r.nextInt(30) - 15.0, 10.0, r.nextInt(30) - 15.0, 1.0, null, true)
            }
            Thread.sleep(3000)
            async {
                Thread.sleep(15000)
                sync {
                    points.forEach { p ->
                        repeat(22) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                }
                Thread.sleep(6000)
                drawText("Happy")
                Thread.sleep(4000)
                drawText("New Year!")
                Thread.sleep(5000)
                drawText("My Friend.")
            }
            var i = 0.0
            while (i < end) {
                sync {
                    repeat(14) {
                        repeat(4) {
                            val angel = (PI / 2) * it
                            genFW(center0.clone().add(cos(i + angel) * radius, 10.0, sin(i + angel) * radius), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                    points.forEach { p ->
                        repeat(14) {
                            genFW(f(p, 25), FireworkEffect
                                    .builder()
                                    .flicker(true)
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withColor(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .withFade(randomColor())
                                    .with(randomType())
                                    .build())
                        }
                    }
                }
                Thread.sleep(700)
                i += step3
            }
            Thread.sleep(3000)
            drawText("Thank you")
            Thread.sleep(4000)
            drawText("for")
            Thread.sleep(4000)
            drawText("watching.")
            Thread.sleep(3000)
            rain(Particle.FIREWORKS_SPARK)
        }
        return true
    }

    private fun f(l: Location, r: Int = 5) = l.clone().add((Math.random() - 0.5) * r * 2, 10.0, (Math.random() - 0.5) * r * 2)

    private fun sync(fn: () -> Unit) = server.scheduler.runTask(this, fn)
    private fun async(fn: () -> Unit) = server.scheduler.runTaskAsynchronously(this, fn)

    private fun draw(l: Location, radius: Int, step: Double, type: Particle) {
        var j = 0.0
        while (j < end) {
            var i = 0.0
            val r = radius * cos(j)
            val y = sin(j) * radius
            while (i < end) {
                i += step
                world.spawnParticle(type, l.clone().add(cos(i) * r, y, sin(i) * r), 0, 0.0, 0.0, 0.0, 1.0, null, true)
            }
            j += step
        }
    }

    private fun genFW(l: Location, effect: FireworkEffect, power: Int = if (Math.random() > 0.5) 2 else 1) {
        val fw = world.spawnEntity(l, EntityType.FIREWORK) as Firework
        val fwm = fw.fireworkMeta
        fwm.addEffect(effect)
        fwm.power = power
        fw.fireworkMeta = fwm
    }

    private fun title(title: Title) {
        world.players.forEach {
            it.sendTitle(title)
            it.playSound(it.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
        }
    }

    private fun rain(type: Particle) {
        var i = 0.0
        while (i < end) {
            world.spawnParticle(type, center.clone().add(cos(i) * radius, -40.0, sin(i) * radius), 100, 30.0, 10.0, 30.0, 1.0, null, true)
            i += step3
        }
    }

    private fun drawText(str: String) {
        var len = 0
        val text = str.map { MinecraftFont.Font.getChar(it)!! }
        val l = center.clone()
        l.x += text.sumBy { it.height } / 2.0
        text.forEach {
            repeat(it.height) { y ->
                repeat(it.width) { x ->
                    if (it[y, x]) world.spawnParticle(Particle.FALLING_LAVA, l.clone().add(-(x.toDouble() + len), 0.0, y.toDouble()), 150, 0.3, 0.0, 0.3, 1.0, null, true)
                }
            }
            len += it.width
            len += 2
        }
    }

    private fun genHeart(l: Location) {
        val vt = 0.01
        var i = 0.0
        while (i < end) {
            val z = cos(i) * 13 - 5 * cos(2 * i) - 2 * cos(3 * i) - cos(4 * i)
            world.spawnParticle(Particle.CLOUD, l.clone().add(sin(i).pow(3) * 16, 0.0, -z), 0, randomN(), 2.0, randomN(), 1.0, null, true)
            i += vt
        }
    }

    private fun scatter(radius: Int, step: Double, type: Particle, times: Int) {
        var j = 0.0
        val loc = center.clone().add(r.nextInt(80) - 40.0, r.nextInt(40) - 20.0, r.nextInt(80) - 40.0)
        while (j < end) {
            var i = 0.0
            val r2 = radius * cos(j)
            val y = sin(j) * radius
            while (i < end) {
                i += step
                world.spawnParticle(type, loc, times, cos(i) * r2, y, sin(i) * r2, 1.0, null, true)
            }
            j += step
        }
    }

    @EventHandler
    private fun onEntitySpawn(e: EntitySpawnEvent) {
        if (e.entityType == EntityType.PHANTOM) e.isCancelled = true
    }

    @EventHandler
    private fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {
        if (e.damager.type == EntityType.FIREWORK) e.isCancelled = true
    }

    override fun onDisable() {
        blocks.clear()
    }
}
