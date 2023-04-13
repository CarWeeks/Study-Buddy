# Study Buddy

## An interactive way to keep studying fun and productive

This application is intended to provide a way for those who often work on computers 
*(computer scientists, computer science students)* to stay productive. The study buddy
is a digital pet that helps manage your **health** and **wellness** as you study/work.

The study buddy is a pet that will have four primary attributes:
- Health
- Food
- Energy
- Happiness

These attributes will interact with one another to create a digital pet that requires
care and attention, with the goal of reminding anyone who has been studying or working
for a while to take a break every once in a while. In addition to taking breaks, the study
buddy will help keep users from feeling lonely while working.

How it will work: the **food** bar will naturally decline as time passes, meaning that 
the user's buddy will get more and more hungry. If the buddy's food bar completely
empties, it will start losing health, and if it runs out of health it will eventually die.
The **health** bar will be non-replenishing, which means once your buddy loses health
you will have to be extra sure not to let it lose anymore!
Your past buddies will then be stored in the Study Buddy Cemetery, a list of all of
your past buddies and how long you were able to keep them alive. You will then be
able to access the cemetery at any time.
The **happiness** bar is much like the health bar, however the user will be able to increase
their pet's happiness by feeding it and keeping it alive longer!
And finally, the energy bar will currently have very little meaning, however in future
additions to the application the **energy** bar will have much more importance. In future
variations, you will be able to play fun mini-games with your buddy which will use energy
and increase happiness at a much faster rate. This will require you to keep your pet fed
so that it will have more energy for future games!

I chose this style of application for myself as well as those around me, school colleagues,
work-place colleagues, and friends! I found that majority of today's world requires the use
of computers, often many hours at a time, and thought it would be beneficial to make their
experience more enjoyable--and even a little-bit fun! This project specifically sparks my
interest as it is a way to make something that people can enjoy and find useful as well as
being a fun personal project that is designed in a way that allows me to add features and
more functions later. The goal is ultimately to help others stay focused while working on 
their computers.

## User Stories

- As a user I want to be able to create and name a new Buddy
- As a user I want to be able to store an arbitrarily long list of my past buddies
- As a user I want to be able to access my list of previous Buddies and see how long I kept each one alive
- As a user I want to be able to feed my study Buddy
- As a user I want to be able to kill my current Buddy
- As a user I want to decide whether my Buddy is stored in the graveyard when it dies
- As a user I want to be able to save my current Buddy and the graveyard if I choose to do so
- As a user I want to be able to reload my saved data and resume where I left off from the save if I choose to do so
- As a user I want to be able to clear my graveyard

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by entering a name in the TextField and
pressing the "Make new Buddy" button. This will create a Buddy (X), you can then press kill or let it die naturally,
once it is dead you will be prompted with the choice to add the Buddy to the graveyard (Y). This will let you add your
Buddy (X) to the graveyard (Y) if you desire to do so.
- You can generate the second required action related to adding Xs to a Y by pressing the "See graveyard" button in the
main options panel, which will display all of the current Buddies (Xs) in your graveyard (Y), once you are in this
screen, you can press the button "Clear graveyard" which will delete all of the Buddies currently displayed (Xs) and
create a new empty graveyard (Y).
- You can locate the visual component by entering a name in the TextField and
  pressing the "Make new Buddy" button. This will display your current Buddy as an image, it will be
a happy image while the Buddy's food is over half full, and a sad image when it is below.
- You can save the state of my application by pressing the "Save" button while in the window with your current Buddy.
- You can reload the state of my application by pressing the "Load previous state" button main options window.

# Phase 4: Task 2

- Wed Apr 12 22:54:42 PDT 2023: Bob died.
- Wed Apr 12 22:54:55 PDT 2023: Bob was added to the graveyard.
- Wed Apr 12 22:55:01 PDT 2023: Steve died.
- Wed Apr 12 22:55:06 PDT 2023: Graveyard cleared.
- Wed Apr 12 22:55:12 PDT 2023: Robert died.
- Wed Apr 12 22:55:13 PDT 2023: Robert was added to the graveyard.


# Phase 4: Task 3

Looking at my diagram there are a few things I would refactor if I had more time and were to go back and change my
program. The first and primary thing I would refactor is how my program uses the CurrState class. I had to make this
class as a work-around in order to access and change just one current instance of a Buddy (current Buddy) and one
current instance of the Graveyard class (current Graveyard) between all of my JPanel classes. However, I did this 
before lecture on how to create a singleton, hence my implementation is what a singleton should be used for, but the
"homemade" version. If I could go back I would change this implementation to be a singleton in the model package,
as I believe this would be the cleanest and most optimal way to improve my program's design.

I also experienced a lot of complications implementing a kill button as well as natural death due to the variety and
coupling of the JPanel classes. If I were to go back and refactor this I would put the code that ticks 
(runs the program) in one class which would additionally contain the other necessary parts of the code that rgard the
Buddy's death, making the work-around for coupling much less, if not zero. And finally, one last change I would make that I feel would make a large difference would be to have anonymous
ActionListeners rather than having most of my JPanels extend ActionListener. This would allow me to implement
reactions to certain buttons being pressed in more localized areas, as well as reducing code duplication.