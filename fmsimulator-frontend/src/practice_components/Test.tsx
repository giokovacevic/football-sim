import React, { Component } from "react";
import { useState } from "react";

type Props = {
    message: string;
};

const Test = ({message}:Props) => {
    const [numbers, setNumbers] = useState<Array<number>>([1, 4, 2, 1, 7, 8, 3, 4, 2]);

    const sortArray = (descending?:boolean):void => {
        const modifiedNumbers = [...numbers];
        for(let i:number=0; i<modifiedNumbers.length-1; i++) {
            for(let j:number=i; j<modifiedNumbers.length; j++) {
                if(descending) {
                    if(modifiedNumbers[i] < modifiedNumbers[j]) {
                        let temp = modifiedNumbers[i];
                        modifiedNumbers[i] = modifiedNumbers[j];
                        modifiedNumbers[j] = temp;
                    }
                }else{
                    if(modifiedNumbers[i] > modifiedNumbers[j]) {
                        let temp = modifiedNumbers[i];
                        modifiedNumbers[i] = modifiedNumbers[j];
                        modifiedNumbers[j] = temp;
                    }
                }
            }
        }
        setNumbers(prevNumbers => modifiedNumbers);
    }

    const generateRandomNumber = (min:number, max:number):number => {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    
    return (
        <>
            <div>
                <div><button onClick={() => sortArray()}>Sort Ascending</button></div>
                <div><button onClick={() => sortArray(true)}>Sort Descending</button></div>
                {numbers.map((value, index) => <div key={index}>{value}</div>)}
            </div>
        </>
    );
};

export default Test;