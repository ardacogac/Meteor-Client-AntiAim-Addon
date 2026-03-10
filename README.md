# Anti-Aim Module (YawMod)

A **CS:GO-style Anti-Aim module** for the **Meteor Client** that manipulates player yaw and pitch to create unpredictable rotations.

This module allows players to spin, jitter, or offset their orientation to make tracking and prediction harder.

---

# Features

- Multiple **Yaw Modes**
- Multiple **Pitch Modes**
- Adjustable **Spin Speed**
- Adjustable **Jitter Range**
- Configurable **Yaw Offset**
- Optional **Fake / Real rotation separation**

---

# Yaw Modes

| Mode | Description |
|-----|-------------|
| Spin | Continuously spins the player's body yaw |
| Backwards | Faces directly behind the player |
| Jitter | Random yaw movement within a configurable range |
| Static | Keeps yaw unchanged |
| OffsetStatic | Applies a fixed yaw offset |

---

# Pitch Modes

| Mode | Description |
|-----|-------------|
| Up | Looks straight up |
| Down | Looks straight down |
| Zero | Looks forward |
| Jitter | Randomly switches between up and down |
| Static | Keeps the current pitch |

---

# Settings

### Spin Speed
Controls the **rotation speed in Spin mode**

Default: `180`  
Range: `140 - 230`

### Jitter Range
Controls the **random deviation amount in Jitter mode**

Default: `45`  
Range: `0 - 180`

### Yaw Offset
Applies a **fixed yaw offset to rotations**

Range: `-180 to 180`

### Separate Fake
Separates **body yaw (fake)** and **head yaw (real)**

When enabled:
- Body rotates using anti-aim
- Head keeps the real direction

---
