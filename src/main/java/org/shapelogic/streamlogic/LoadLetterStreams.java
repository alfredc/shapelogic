package org.shapelogic.streamlogic;

import static org.shapelogic.logic.CommonLogicExpressions.*;

import org.shapelogic.calculation.Calc1;
import org.shapelogic.calculation.RootMap;
import org.shapelogic.polygon.Polygon;
import org.shapelogic.streams.ListCalcStream1;
import org.shapelogic.streams.ListStream;
import org.shapelogic.streams.NumberedStream;
import org.shapelogic.streams.StreamFactory;
import org.shapelogic.util.Constants;

/** Create letter streams.
 * 
 * Based on LetterTaskFactory.<br /> 
 * 
 * Should create all the rules used for letter matching.<br />
 * 
 * Contains 2 sets of rules for letter matching<br />
 * 
 * 1: A simple set that only works for straight letters using simple properties<br />
 * 2: A more complex set of rules works for straight and curved letters <br />
 * This uses annotations <br />
 * 
 * @author Sami Badawi
 *
 */
public class LoadLetterStreams {

	/** Helper method to create one rule in one letter. 
	 * 
	 * @param letter to define rule for
	 * @param streamName what the required stream for this rule is called in RootMap
	 * @param value constraint value
	 * @param letterFilter if only one rule should be generated this should be set to a letter 
	 */
	public static void rule(String letter, String streamName, int value, String letterFilter) {
		if (letterFilter != null && !letterFilter.equalsIgnoreCase(letter))
			return;
		StreamFactory.addToAndListStream0(letter, streamName, "==",	value);
	}
	
	/** Load all the required streams for the letter matcher to work.
	 * <br />
	 * In order for this to work the polygons have to be defined first.
	 */
	public static void loadStreamsRequiredForLetterMatch() {
		
		//In order for this to work the polygons have to be defined first
		NumberedStream<Polygon> polygons = (NumberedStream<Polygon>) RootMap.get(Constants.POLYGONS);

		Calc1<Polygon, Integer> pointCountCalc1 = new Calc1<Polygon, Integer>() {
			@Override
			public Integer invoke(Polygon input) {
				return input.getPoints().size();
			}
		};
		ListStream<Integer> pointCountStream = 
			new ListCalcStream1<Polygon, Integer>(pointCountCalc1,polygons); 
		RootMap.put(POINT_COUNT,pointCountStream);
		
		Calc1<Polygon, Integer> lineCountCalc1 = new Calc1<Polygon, Integer>() {
			@Override
			public Integer invoke(Polygon input) {
				return input.getLines().size();
			}
		};
		ListStream<Integer> lineCountStream = 
			new ListCalcStream1<Polygon, Integer>(lineCountCalc1,polygons); 
		RootMap.put(LINE_COUNT,lineCountStream);
		
		Calc1<Polygon, Integer> horizontalLineCountCalc1 = new Calc1<Polygon, Integer>() {
			@Override
			public Integer invoke(Polygon input) {
				return input.getHorizontalLines().size();
			}
		};
		ListStream<Integer> horizontalLineCountStream = 
			new ListCalcStream1<Polygon, Integer>(horizontalLineCountCalc1,polygons); 
		RootMap.put(HORIZONTAL_LINE_COUNT,horizontalLineCountStream);
		
		Calc1<Polygon, Integer> verticalLineCountCalc1 = new Calc1<Polygon, Integer>() {
			@Override
			public Integer invoke(Polygon input) {
				return input.getVerticalLines().size();
			}
		};
		ListStream<Integer> verticalLineCountStream = 
			new ListCalcStream1<Polygon, Integer>(verticalLineCountCalc1,polygons); 
		RootMap.put(VERTICAL_LINE_COUNT,verticalLineCountStream);
		
		Calc1<Polygon, Integer> endPointCountCalc1 = new Calc1<Polygon, Integer>() {
			@Override
			public Integer invoke(Polygon input) {
				return input.getEndPointCount();
			}
		};
		ListStream<Integer> endPointCountStream = 
			new ListCalcStream1<Polygon, Integer>(endPointCountCalc1,polygons); 
		RootMap.put(END_POINT_COUNT,endPointCountStream);
	}
	
	public static void makeLetterStream(String letterFilter) {
		rule("A", POINT_COUNT, 5, letterFilter);
		rule("A", LINE_COUNT, 5, letterFilter);
		rule("A", HORIZONTAL_LINE_COUNT, 1, letterFilter);
		rule("A", VERTICAL_LINE_COUNT, 0, letterFilter);
		rule("A", END_POINT_COUNT, 2, letterFilter);

		rule("E", POINT_COUNT, 6, letterFilter);
		rule("E", LINE_COUNT, 5, letterFilter);
		rule("E", HORIZONTAL_LINE_COUNT, 3, letterFilter);
		rule("E", VERTICAL_LINE_COUNT, 2, letterFilter);
		rule("E", END_POINT_COUNT, 3, letterFilter);

		rule("F", POINT_COUNT, 5, letterFilter);
		rule("F", LINE_COUNT, 4, letterFilter);
		rule("F", HORIZONTAL_LINE_COUNT, 2, letterFilter);
		rule("F", VERTICAL_LINE_COUNT, 2, letterFilter);
		rule("F", END_POINT_COUNT, 3, letterFilter);

		rule("H", POINT_COUNT, 6, letterFilter);
		rule("H", LINE_COUNT, 5, letterFilter);
		rule("H", HORIZONTAL_LINE_COUNT, 1, letterFilter);
		rule("H", VERTICAL_LINE_COUNT, 4, letterFilter);
		rule("H", END_POINT_COUNT, 4, letterFilter);

		rule("I", POINT_COUNT, 2, letterFilter);
		rule("I", LINE_COUNT, 1, letterFilter);
		rule("I", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("I", VERTICAL_LINE_COUNT, 1, letterFilter);
		rule("I", END_POINT_COUNT, 2, letterFilter);

		rule("K", POINT_COUNT, 5, letterFilter);
		rule("K", LINE_COUNT, 4, letterFilter);
		rule("K", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("K", VERTICAL_LINE_COUNT, 2, letterFilter);
		rule("K", END_POINT_COUNT, 4, letterFilter);

		rule("L", POINT_COUNT, 3, letterFilter);
		rule("L", LINE_COUNT, 2, letterFilter);
		rule("L", HORIZONTAL_LINE_COUNT, 1, letterFilter);
		rule("L", VERTICAL_LINE_COUNT, 1, letterFilter);
		rule("L", END_POINT_COUNT, 2, letterFilter);

		rule("M", POINT_COUNT, 5, letterFilter);
		rule("M", LINE_COUNT, 4, letterFilter);
		rule("M", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("M", VERTICAL_LINE_COUNT, 2, letterFilter);
		rule("M", END_POINT_COUNT, 2, letterFilter);

		rule("N", POINT_COUNT, 4, letterFilter);
		rule("N", LINE_COUNT, 3, letterFilter);
		rule("N", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("N", VERTICAL_LINE_COUNT, 2, letterFilter);
		rule("N", END_POINT_COUNT, 2, letterFilter);

		rule("T", POINT_COUNT, 4, letterFilter);
		rule("T", LINE_COUNT, 3, letterFilter);
		rule("T", HORIZONTAL_LINE_COUNT, 2, letterFilter);
		rule("T", VERTICAL_LINE_COUNT, 1, letterFilter);
		rule("T", END_POINT_COUNT, 3, letterFilter);

		rule("V", POINT_COUNT, 3, letterFilter);
		rule("V", LINE_COUNT, 2, letterFilter);
		rule("V", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("V", VERTICAL_LINE_COUNT, 0, letterFilter);
		rule("V", END_POINT_COUNT, 2, letterFilter);

		rule("W", POINT_COUNT, 5, letterFilter);
		rule("W", LINE_COUNT, 4, letterFilter);
		rule("W", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("W", VERTICAL_LINE_COUNT, 0, letterFilter);
		rule("W", END_POINT_COUNT, 2, letterFilter);

		//Same as W
		rule("X", POINT_COUNT, 5, letterFilter);
		rule("X", LINE_COUNT, 4, letterFilter);
		rule("X", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("X", VERTICAL_LINE_COUNT, 0, letterFilter);
		rule("X", END_POINT_COUNT, 4, letterFilter);
		
		rule("Y", POINT_COUNT, 4, letterFilter);
		rule("Y", LINE_COUNT, 3, letterFilter);
		rule("Y", HORIZONTAL_LINE_COUNT, 0, letterFilter);
		rule("Y", VERTICAL_LINE_COUNT, 1, letterFilter);
		rule("Y", END_POINT_COUNT, 3, letterFilter);

		rule("Z", POINT_COUNT, 4, letterFilter);
		rule("Z", LINE_COUNT, 3, letterFilter);
		rule("Z", HORIZONTAL_LINE_COUNT, 2, letterFilter);
		rule("Z", VERTICAL_LINE_COUNT, 0, letterFilter);
		rule("Z", END_POINT_COUNT, 2, letterFilter);
	}
}