program HanoiskieBashni;

{$APPTYPE CONSOLE}
{$R *.res}

uses
  System.SysUtils;

const
    maxRingCount = 3;
Type
    ArrayOfRings = array[1..MaxRingCount] of integer;

    TTowers = record
    RingCount:integer;
    Rings: ArrayOfRings;
    end;

    ArrOfTowers = array[1..3] of TTowers;
Var
    Tower: ArrOfTowers;

Procedure Input;
var
    i:integer;
begin
    for i := 1 to maxRingCount do
        Tower[1].Rings[i] := maxRingCount - i + 1;
     Tower[1].RingCount := maxRingCount;
     Tower[2].RingCount := 0;
     Tower[3].RingCount := 0;
end;

Procedure Output;
var
  i,j,Num: Integer;
begin
    for i := 1 to 3 do
    begin
        Num := Tower[i].RingCount;
        if Num < MaxRingCount then
        begin
            for j := Num + 1 to MaxRingCount do
                Tower[i].Rings[j] := 0;
        end;
    end;
    Writeln(' _____________________________');
    Writeln('| Башня 1 | Башня 2 | Башня 3 |');
    Writeln('|_________|_________|_________|');

    for i := MaxRingCount downto 1 do
        Writeln('| ',Tower[1].Rings[i]:4,'    | ',Tower[2].Rings[i]:4,'    | ',Tower[3].Rings[i]:4,'    |');
    Writeln('|_________|_________|_________|');
    Writeln;
end;

Procedure Move(var Tower:ArrOfTowers; var source:integer;var dest:integer);
begin
    inc(Tower[dest].RingCount);
    Tower[dest].Rings[Tower[dest].RingCount]:= Tower[source].Rings[Tower[source].RingCount];
    dec(Tower[source].RingCount);
    Output;
end;

Procedure Hanoi(Num: integer; source:integer; dest:integer; aux:integer);
begin
    if Num = 1 then
        Move(Tower,source,dest)
    else
    begin
        Hanoi(Num - 1,source,aux,dest);
        Move(Tower,source,dest);
        Hanoi(Num - 1,aux,dest,source);
    end;
end;

begin
    Input;
    Output;
    Hanoi(maxRingCount,1,3,2);
    readln;
end.
