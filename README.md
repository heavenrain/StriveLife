# StriveLife APP
**Strive for a better Life**

---

## The Design Motivation of the StriveLife APP

- **Existing To-Do-List APP**
  - Various functions allow users to manage cumbersome to-do lists.
  - Reminder and record orientation.

- **StriveLife APP**
  - The function is simple and only includes functions related to forming habits.
  - The friend competition function uses competitions and rewards to urge users to develop good habits with their friends.
  - Motivational and entertainment orientation.

---

## APP Functions

- Register and login
- Friends list
- Competition
- To-Do-List

---

## APP Functions - Register and login

- You can login after registering.

---

## APP Functions - Friends list

- List all added friends.
- Enter your friend ID to join your friends.
- The friends list can display the reward stickers obtained in the competition.
- Increase users' determination to complete their daily list by showing off beautiful stickers.

---

## APP Functions - Competition

- The contents of the newly added competition (competition name, achievement of goals, progress requirements, selection of contestants) are all customized by the creator of the competition group.
  
**Example:**
  - Competition name: Drink more water when you have nothing to do.
  - Goal achieved: 15 (days).
  - Schedule requirement: 2000(c.c.).
  - Time limit: 06:00 (you need to fill in the completion progress on the To-Do-List page before this time every day).
  - Choose reward: Reward photo sticker (drink 2000c.c. of water every day and get it after 15 days).
  - Competition members: The competition group creator selects friends and invites them to join the competition.

---

## APP Functions - To-Do-List

- **To-Do-List function**
  - On this page, you can edit the photo of the rewards you have received. When your friends check your status through the friend list, they can see the photo of the rewards you have received.

- **Add a new To-Do-List item**
  - After the competition is established, items corresponding to the competition category will be added to each competition member's To-Do-List page, allowing users to check whether to complete or fill in the actual number of completions.

---

## Database

- MySQL, PHP
- Five tables (gammer, contest, friend_relation, award_record, attendance)

---

## Difficulties Faced by APP and Solutions

- **How to make players continue to use the APP?**
  - Through competition
    - The ranks are based on the points they have earned, allowing friends to compete and compare each other's rankings.
  - Through rewards
    - When a player achieves an achievement in a competition, the player will receive a reward sticker corresponding to the achievement.
    - Players display their avatar in a conspicuous way to let everyone know how hard they work in life (e.g., Player A who continues to exercise for 30 days will receive corresponding reward stickers).

- **Privacy issues in competition situations**
  - Set a fixed time to refresh scores and rankings, so that players in the competition can know whether each other has achieved it, but the detailed status of the players' achievement will not be displayed.
  - e.g., It is refreshed at 6:00 am every day. Player A can know that player B did not achieve it the previous day in the "early bed" competition but cannot know player B's specific bedtime.

---

## How to Design "Compete with Friends"

- Each player needs to create an account first, and then the system will log the player into the Player Database.
- Players can add friends to the friend list by entering their friend ID.
- Players can create a competition and invite friends to join the competition. The system will log the competition into the Competition Database and link it to all diners.

