program Perm;

{$APPTYPE CONSOLE}

{$R *.res}

uses
  System.SysUtils;

Const
    N = 1000;
Type
    Tarr = array[1..N] of integer;

procedure Input(var Arr:Tarr);
var
    i: integer;
begin
    for i := 1 to N do
        Arr[i] := i;
end;

procedure Swap(var a,b:integer);
var
    temp : integer;
begin
    temp := a;
    a := b;
    b := temp;
end;

procedure Perestanovki(Arr:TArr; Begining, N:integer);

procedure Perm(Begining:integer);
var i, j :integer;
begin
    if Begining = N then
    begin
        for i := 1 to N do
            write(Arr[i]);
        writeln;
    end
    else
    begin
        for j := Begining + 1 to N do
        begin
            Swap(Arr[Begining + 1],arr[j]);
            Perm(Begining + 1);
            Swap(Arr[Begining + 1],arr[j]);
        end;
    end;
end;

begin
    Perm(Begining);
end;


var
    Arr: Tarr;
begin
    Writeln('Перестановки среди ',n,' элементов:');
    Input(Arr);
    Perestanovki(Arr,0,N);
    readln;
end.
