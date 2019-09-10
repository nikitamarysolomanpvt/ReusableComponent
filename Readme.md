## Objective

Demo for creating reusable components in android application. These components should be similar to `TextView` or `Button` and should be easy enough just like any other view.

## Process of creating any `CustomCompoundView`
1. Create an XML which will contain the UI for customComponent. Check `component_audio` for sample.
2. Create a class which extends the rootViewGroup present in the above XML. Check `AudioComponent` for sample.
3. You can either now use this component directly in XML or you can control that from Java. Adding this to XML is easy and therefore this sample code contains example with JAVA code only.
4. `ExplorationPlayerFragment` contains two methods `addAudioComponent` and `removeAudioComponent`, these methods are responsible for controllling this component in rootViewGroup fo `Fragment`.
5. Create an interface which can provide two-directional flow of information/data between the component and fragment/activity. Check `AudioInterface` for sample.

## Component use in XML vs. Java
Use component in XML when you think that the component will be used in that fragment/activity. That way it will be easier to control that component in much easier code.

Use component from Java code when you are not sure whether that component will be required in the fragment/actiivty or not. This approach helps in keeping code usage on minimal level at the same time you can add the sample component as many times as you want. Check `ContentCardComponent` for muliple usage.

## Contact
You can connect with me on rajattalesra4914@gmail.com
