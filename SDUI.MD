# SDUI

**S**erver **D**riven **U**ser **I**nterfaces are used to greatly speed of mobile application development.
The backend sends a tree of components for the client to render without further effort required by the developer.
These components can also be bound to pre-defined generic and reusable blocks of logic so that the UI is functional and interactive.
The developer is then only responsible for building out the library of components and logic blocks which can be reused across the application.
Even better these components and especially the logic blocks can be shared between applications.

When very specific business logic is required, this will most likely be implemented on the backend as is often done in an application.
The mobile application becomes very light and thin and mostly only responsible for representation of the data and handling interactions of the user.
The server is what determines what is shown and what will be shown next based on the user's interactions.

## Architecture
Traditional architecture typically looks like the following for a typical android application which needs development for every single screen.

Database [Centrally stores the distributed states of it's users and entities]

Backend Services [Holds the core business rules of a distributed set of features]

Middleware [Handles combining and mapping the logic of multiple services into a single response for the mobile app to consume]

Mobile App Service/Cache [Handles requests and responses of an application]

Mobile App Repository [Handles combining multiple data sources such as backend endpoints and local in memory caches for a feature]

Mobile App ViewModel [Maps the data from the repository into a convenient format the UI can consume and interact with via logic functions]

Mobile App View [Binds to the view model and presents a tree of components which are used to render the UI]

Mobile App Components Library* (May not require development for a particular screen) [Uses data to render UI to the user]

As you can see, this is quite a large list of architectural components that a developer is expected to implement for every screen they work on.
However, with SDUI this gets greatly simplified.

Database [Centrally stores the distributed states of it's users and entities]

Backend Services [Holds the core business rules of a distributed set of features]

Middleware [Handles combining and mapping the logic of multiple services into a single response for the mobile app to consume.
Additionally, the mapping deterministically computes the tree of UI elements their associated logic]

Mobile App Components + Logic Library* (May not require development for a particular screen) [Uses data to render UI to the user]

When using SDUI the list is greatly reduced as it forces you to

A) Develop a powerful, coherent and consistent library of components and logic blocks which can easily be reused across an application.
B) Instead of spreading logic between server and client, it's centralized in the backend to reduce repetition and complexity

## Tradeoffs
Considered SDUI is not considered to be the standard way for mobile applications to be developed by many teams; there must be some sort of tradeoff.
1) There are few modern implementations of SDUI that are open source for Android. 
    The most popular implementation of a SDUI library is developed by Yandex with next to no other implementations.
    This means if Yandex's library isn't a good fit then there are no alternatives other than rolling out your own.
2) Skill Issue—It will sound harsh, but some engineering teams from FAANG companies already extensively use SDUI.
    It should be no surprise that these levels of devs can handle building a good sdui library and gain the benefits. 
    Surprisingly, I have heard from other engineering teams that SDUI is too hard or too complex, 
    This is almost definite because they are not familiar with the concepts of SDUI and implemented a poor library.
3) Increased LOC required to be developed on the middleware, and increased request and response cycles on said middleware.

## Conclusion

The main reason it's not been adopted by companies without advanced engineering teams is because it's too easy to completely mess up the SDUI library implementation.
Doing so will make the result of working with SDUI harder than working without it.
However, if the initial library is nailed down, then it can easily double the amount of feature work that can be done.

There are two hidden advantages to SDUI that are not immediately clear but are game changers.

All the custom complexity, state and logic are now only being defined on the middleware layer. 
If I could tell you how often developers have no clue whether undesired behavior in an app is due to the backend, client or both, it's outstanding.
Now the middleware is the largest culprit since it's the main thing that is being changed from screen to screen, while the component/logic library is more stable.
Because the backend now determines the functionality of the app, there is no need for time-consuming back and forth between client and server developers to stay in sync. 

The second hidden advantage is that when SDUI is adopted at the product or even the executive level, a greater focus can be put on getting bang for the buck out of features.
If each screen needs a complete custom stack of development, it is likely for the product to make decisions that make screens diverge in functionality.
Given screen B will take one week to build but is very similar to screen A, and screen C will take 1.5 weeks to build but is quite distinct from screen A.
Product will easily choose to build screen C over B if it's a better fit for the product. 
The real problem is that the cost to build screen B that's similar to screen A should have more than a half a week savings considering the screens are similar.
However because reuse in the app is poor, even similar screens need to be developed from scratch.
In the SDUI paradigm screen B would take a day, now it's a no-brainer to choose screen B.
This means that the product can settle on fewer but more robust patterns which will greatly increase the productivity of the team.

## How this SDUI library works?
The server creates a tree of components and logic blocks in the format of an SConfig file
This tree of elements being rendered should be pretty self-explanatory. Each component can have a list of triggers added to it.
A trigger is basically an event listener that will be called when the user or system meets the conditions to trigger it, like onclick.
In the SConfig each component can be bound to any number of triggers; the only caution is that the actual implementation of the component may not use some triggers.
A good example is an OnScroll trigger is unlikely to be used by a component that is not a scrollable view.
Conversely, the SConfig does not have to supply a trigger for each trigger a component supports; it's completely flexible.

The SConfig will then bind an action which is precomputed logic to a trigger.
Meaning that any combination of triggers and actions can be bounded together for maximum flexibility.
No behavior is hardcoded in a component. Actions are very robust in that multiple components can be involved in a single action.
A great example is the MatchValid action when combined with the OnUpdatedState trigger.
A button can have an OnUpdatedState trigger which takes in a list of ids which means the bound action will get executed each time any of those target components update state.
The action MatchValid is used to determine if the button is valid/enabled based on each target component is also valid or not.
This is used to enable a continued button on a form only after all the fields are valid.

Another concept that is important to understand is called Meta, these contain data that is not relevant for the current SConfig but is relevant to the middleware.
For example, this can include information like tokens, ids and other important identifiable information. 
This works hand in hand with the SendState action, which, when executed, takes the current state of the screen and sends it to the middleware.
With the current state of the screen and the meta-information, the middleware should have all the information it needs to compute what should be rendered on the screen next.
To reduce the overhead of sending back all the state of a screen back, the action can decide which components and meta need to be sent back via their ID.

Meta is cached on the application level, so meta from screens back can be recalled at any time. 
Of course, cache invalidation can be tricky, but it's not a problem that's unique to SDUI. 
If anything, SDUI brings this concern to the forefront, so it can more simply be managed.
Typically, components and meta are bound to scopes via the ID constructor. 
This allows for multiple of the same components or screens with the same id to have different states.
For example, a screen with a list of items which navigates to a detail screen when an item is clicked.
The detail screen has the same id regardless of which item is clicked. But the scope of the screen will be bound to the item's id.
The cache manager can see it has stored information on the five-item details screen, and maybe 5 is the limit. 
Which means it will remove the first item when another details screen is navigated to.
The cache manager is highly configurable; it could also move items based on a time limit or even on a global screen cache limit.

The library for now is still in its infancy, but hopefully be the end of the year an alpha version will be released which is capable 
of handling at least 90% of the use cases of a moderate complexity application.
When it's not possible to use SDUI, it's always possible to just fall back to regular client side development of a screen.
The goal is to be able to build a fully featured app 100% with SDUI before this is released to beta.
Of course, the library should be extended to meet each application need with more components and logic blocks.
Merely adding components and logic blocks is 10% of the effort of developing from scratch, so it's a massive win even if a lot are required.