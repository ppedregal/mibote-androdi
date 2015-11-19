package com.thinknowa.botin.components.slideritem.model;


public interface InstanceVisitor<R>
{
	R visit(Track track);

//	R visit(FSobject FSobject);
}
